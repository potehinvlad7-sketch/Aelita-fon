package com.artraccoon.aelitafon.systemagent

import com.artraccoon.aelitafon.apps.AppLauncher
import com.artraccoon.aelitafon.apps.AppRepository
import com.artraccoon.aelitafon.core.AelitaLocalCore
import com.artraccoon.aelitafon.core.LocalCommand
import com.artraccoon.aelitafon.core.LocalCommandParser
import com.artraccoon.aelitafon.device.CapabilityRegistry
import com.artraccoon.aelitafon.device.DeviceStateReader
import com.artraccoon.aelitafon.logs.ActionLogEntry
import com.artraccoon.aelitafon.logs.ActionLogStore
import com.artraccoon.aelitafon.permissions.PermissionCenter
import com.artraccoon.aelitafon.policy.PolicyDecision
import com.artraccoon.aelitafon.policy.PolicyEngine

class DefaultAelitaSystemAgent(
    private val deviceStateReader: DeviceStateReader,
    private val capabilityRegistry: CapabilityRegistry,
    private val policyEngine: PolicyEngine,
    private val localCore: AelitaLocalCore,
    private val appRepository: AppRepository,
    private val appLauncher: AppLauncher,
    private val actionLogStore: ActionLogStore,
    private val permissionCenter: PermissionCenter,
    private val parser: LocalCommandParser = LocalCommandParser(),
) : AelitaSystemAgent {
    override fun getStatus(): SystemAgentStatus {
        val snapshot = deviceStateReader.readSnapshot()
        val localSummary = localCore.getSummary()
        return SystemAgentStatus(
            appMode = snapshot.appMode,
            romMode = "ROM integration not active",
            launcherModeKnown = "HOME launcher support is declared",
            privilegedMode = "not active",
            backgroundMode = "not implemented",
            localOnly = snapshot.localOnly,
            externalAiApiEnabled = false,
            cloudBackendEnabled = false,
            userMessage = "Аэлита работает как обычное Android-приложение/launcher-прототип. " +
                "Системные привилегии и ROM-интеграция ещё не активны.\n" +
                "Пакет: ${snapshot.packageName}\n" +
                "Локальный режим: ${if (snapshot.localOnly) "да" else "нет"}\n" +
                "Фоновый режим: не реализован.\n" +
                "Открой раздел Права, чтобы увидеть уровни доступа Аэлиты.\n" + localSummary.userMessage,
        )
    }

    override fun handleCommand(input: String): SystemAgentResult {
        val command = parser.parse(input)
        return when (command) {
            LocalCommand.ShowStatus -> statusResult()
            LocalCommand.ShowCapabilities -> permissionCenterResult()
            LocalCommand.ShowPermissionCenter -> permissionCenterResult()
            LocalCommand.ListApps -> listAppsResult()
            is LocalCommand.SearchApp -> searchAppsResult(command.query)
            is LocalCommand.LaunchApp -> launchAppResult(command.query)
            LocalCommand.RomInfo -> romResult()
            else -> localCoreResult(input)
        }
    }

    override fun openSection(sectionName: String): SystemAgentResult {
        val normalized = sectionName.trim().lowercase()
        if (normalized == "голос") {
            localCore.handleInput("голосовой placeholder")
            return openSectionResult("Голосовой режим будет добавлен позже.")
        }
        return when (normalized) {
            "память" -> localCoreResult("память")
            "проекты" -> localCoreResult("проекты")
            "приложения" -> listAppsResult()
            "система" -> statusResult()
            "журнал" -> localCoreResult("журнал")
            "предложения" -> localCoreResult("предложения")
            "права" -> permissionCenterResult()
            else -> openSectionResult("Раздел пока готовится: $sectionName")
        }
    }

    override fun getActionLog(limit: Int): List<ActionLogEntry> = localCore.getActionLog(limit)

    private fun statusResult(): SystemAgentResult {
        val decision = policyEngine.evaluate("SHOW_STATUS")
        return result(getStatus().userMessage, "SHOW_STATUS", decision)
    }

    private fun capabilitiesResult(): SystemAgentResult {
        val decision = policyEngine.evaluate("SHOW_CAPABILITIES")
        val message = capabilityRegistry.listCapabilities().joinToString(separator = "\n") {
            "${it.level}. ${it.title}: ${it.status}, риск: ${it.riskLabel}. ${it.description}"
        }
        return result("Текущие уровни доступа Аэлиты:\n$message\n\nВажно: сеть, облако, внешние AI API, Accessibility, Notification Listener, root и ROM hooks не активны.", "SHOW_CAPABILITIES", decision)
    }

    private fun permissionCenterResult(): SystemAgentResult {
        val decision = policyEngine.evaluate("SHOW_PERMISSION_CENTER")
        actionLogStore.add("Открыт Permission Center", "permissions", decision.riskLabel)
        return result(permissionCenter.getUserMessage(), "SHOW_PERMISSION_CENTER", decision)
    }

    private fun romResult(): SystemAgentResult {
        val decision = policyEngine.evaluate("SHOW_STATUS")
        return result(
            "AelitaOS — целевое ROM-направление для Xiaomi 11 Lite 5G NE / lisa. " +
                "Сейчас ROM-интеграция не активна, ROM source tree отсутствует, buildability не заявляется.",
            "SHOW_STATUS",
            decision,
        )
    }

    private fun listAppsResult(): SystemAgentResult {
        val decision = policyEngine.evaluate("LIST_APPS")
        val apps = appRepository.getLaunchableApps()
        actionLogStore.add("Показан список приложений", "apps", decision.riskLabel)
        val list = apps.take(30).joinToString("\n") { "• ${it.label} (${it.packageName})" }
        val message = if (apps.isEmpty()) {
            "Launchable-приложения не найдены. Это обычный Android-запуск приложений, не ROM-контроль."
        } else {
            "Приложения (${apps.size}):\n$list\n\nЭто обычный Android-запуск приложений, не ROM-контроль."
        }
        return result(message, "LIST_APPS", decision)
    }

    private fun searchAppsResult(query: String): SystemAgentResult {
        val decision = policyEngine.evaluate("SEARCH_APPS")
        val search = appRepository.searchApps(query)
        actionLogStore.add("Поиск приложения: ${query.ifBlank { "<пусто>" }}", "apps", decision.riskLabel)
        return result(search.userMessage, "SEARCH_APPS", decision)
    }

    private fun launchAppResult(query: String): SystemAgentResult {
        val search = appRepository.searchApps(query)
        if (query.isBlank() || search.matches.isEmpty()) {
            val decision = policyEngine.evaluate("LAUNCH_APP_FAILED")
            actionLogStore.add("Не удалось запустить приложение: ${query.ifBlank { "<пусто>" }}", "apps", decision.riskLabel)
            return result("Не нашла launchable-приложение: ${query.ifBlank { "<пустой запрос>" }}", "LAUNCH_APP_FAILED", decision)
        }
        val normalizedQuery = query.trim().lowercase()
        val exactMatches = search.matches.filter {
            it.label.lowercase() == normalizedQuery || it.packageName.lowercase() == normalizedQuery
        }
        val launchCandidate = exactMatches.singleOrNull() ?: search.matches.singleOrNull()
        if (launchCandidate == null) {
            val decision = policyEngine.evaluate("LAUNCH_APP_FAILED")
            actionLogStore.add("Несколько приложений подходят под запрос: $query", "apps", decision.riskLabel)
            val candidates = search.matches.joinToString("\n") { "• ${it.label} (${it.packageName})" }
            return result("Нашла несколько вариантов. Уточни команду:\n$candidates", "LAUNCH_APP_FAILED", decision)
        }
        val app = launchCandidate
        val decision = policyEngine.evaluate("LAUNCH_APP")
        val launched = decision.allowed && appLauncher.launchApp(app)
        return if (launched) {
            actionLogStore.add("Запуск приложения: ${app.label}/${app.packageName}", "apps", decision.riskLabel)
            result("Открываю: ${app.label}", "LAUNCH_APP", decision)
        } else {
            val failed = policyEngine.evaluate("LAUNCH_APP_FAILED")
            actionLogStore.add("Не удалось запустить приложение: $query", "apps", failed.riskLabel)
            result("Не удалось запустить приложение через обычный Android Intent: ${app.label}", "LAUNCH_APP_FAILED", failed)
        }
    }

    private fun localCoreResult(input: String): SystemAgentResult {
        val local = localCore.handleInput(input)
        val decision = policyEngine.evaluate(local.actionName).let {
            if (it.allowed) it else PolicyDecision(true, local.riskLabel, "Локальная безопасная команда Local Core.", false)
        }
        return result(local.userMessage, local.actionName, decision)
    }

    private fun openSectionResult(message: String): SystemAgentResult {
        val decision = policyEngine.evaluate("OPEN_SECTION")
        return result(message, "OPEN_SECTION", decision)
    }

    private fun result(message: String, actionName: String, decision: PolicyDecision): SystemAgentResult =
        SystemAgentResult(if (decision.allowed) message else decision.reason, actionName, decision)
}
