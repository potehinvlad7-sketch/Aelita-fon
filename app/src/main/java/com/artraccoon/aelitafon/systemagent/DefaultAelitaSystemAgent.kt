package com.artraccoon.aelitafon.systemagent

import com.artraccoon.aelitafon.core.AelitaLocalCore
import com.artraccoon.aelitafon.core.LocalCommand
import com.artraccoon.aelitafon.core.LocalCommandParser
import com.artraccoon.aelitafon.device.CapabilityRegistry
import com.artraccoon.aelitafon.device.DeviceStateReader
import com.artraccoon.aelitafon.logs.ActionLogEntry
import com.artraccoon.aelitafon.policy.PolicyDecision
import com.artraccoon.aelitafon.policy.PolicyEngine

class DefaultAelitaSystemAgent(
    private val deviceStateReader: DeviceStateReader,
    private val capabilityRegistry: CapabilityRegistry,
    private val policyEngine: PolicyEngine,
    private val localCore: AelitaLocalCore,
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
                "Фоновый режим: не реализован.\n" + localSummary.userMessage,
        )
    }

    override fun handleCommand(input: String): SystemAgentResult {
        val command = parser.parse(input)
        return when (command) {
            LocalCommand.ShowStatus -> statusResult()
            LocalCommand.ShowCapabilities -> capabilitiesResult()
            LocalCommand.AppsPlaceholder -> appControlPlaceholderResult()
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
            "приложения" -> appControlPlaceholderResult()
            "система" -> statusResult()
            "журнал" -> localCoreResult("журнал")
            "предложения" -> localCoreResult("предложения")
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

    private fun romResult(): SystemAgentResult {
        val decision = policyEngine.evaluate("SHOW_STATUS")
        return result(
            "AelitaOS — целевое ROM-направление для Xiaomi 11 Lite 5G NE / lisa. " +
                "Сейчас ROM-интеграция не активна, ROM source tree отсутствует, buildability не заявляется.",
            "SHOW_STATUS",
            decision,
        )
    }

    private fun appControlPlaceholderResult(): SystemAgentResult {
        val decision = policyEngine.evaluate("PLACEHOLDER_APP_CONTROL")
        return result("Запуск приложений будет отдельным PR. Сейчас Аэлита только готовит уровень контроля.", "PLACEHOLDER_APP_CONTROL", decision)
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
