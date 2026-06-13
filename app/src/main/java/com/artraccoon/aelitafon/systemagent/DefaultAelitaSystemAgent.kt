package com.artraccoon.aelitafon.systemagent

import com.artraccoon.aelitafon.device.CapabilityRegistry
import com.artraccoon.aelitafon.device.DeviceStateReader
import com.artraccoon.aelitafon.logs.ActionLogEntry
import com.artraccoon.aelitafon.logs.ActionLogStore
import com.artraccoon.aelitafon.policy.PolicyDecision
import com.artraccoon.aelitafon.policy.PolicyEngine

class DefaultAelitaSystemAgent(
    private val deviceStateReader: DeviceStateReader,
    private val capabilityRegistry: CapabilityRegistry,
    private val policyEngine: PolicyEngine,
    private val actionLogStore: ActionLogStore,
) : AelitaSystemAgent {
    override fun getStatus(): SystemAgentStatus {
        val snapshot = deviceStateReader.readSnapshot()
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
                "Фоновый режим: не реализован.",
        )
    }

    override fun handleCommand(input: String): SystemAgentResult {
        val command = parseCommand(input)
        actionLogStore.add("Команда отправлена: ${input.ifBlank { "<пусто>" }}", "command", "low")
        return when (command) {
            SystemAgentCommand.STATUS -> statusResult()
            SystemAgentCommand.CAPABILITIES -> capabilitiesResult()
            SystemAgentCommand.POLICY -> policyResult()
            SystemAgentCommand.LOG -> logResult()
            SystemAgentCommand.ROM -> romResult()
            SystemAgentCommand.APPS_PLACEHOLDER -> appControlPlaceholderResult()
            SystemAgentCommand.UNKNOWN -> unknownResult()
        }
    }

    override fun openSection(sectionName: String): SystemAgentResult {
        val normalized = sectionName.trim().lowercase()
        if (normalized == "голос") {
            actionLogStore.add("Нажат voice placeholder", "voice", "low")
            return openSectionResult("Голосовой ввод пока является безопасной локальной заглушкой.")
        }
        actionLogStore.add("Shell открыт раздел: $sectionName", "shell", "low")
        return when (normalized) {
            "память" -> openSectionResult("Память будет подключена как локальный модуль позже.")
            "проекты" -> openSectionResult("Проекты будут подключены как локальный модуль позже.")
            "приложения" -> appControlPlaceholderResult()
            "система" -> statusResult()
            "журнал" -> logResult()
            "предложения" -> suggestionsResult()
            else -> openSectionResult("Раздел пока готовится: $sectionName")
        }
    }

    override fun getActionLog(limit: Int): List<ActionLogEntry> = actionLogStore.latest(limit)

    private fun parseCommand(input: String): SystemAgentCommand {
        val text = input.trim().lowercase()
        return when {
            text in setOf("статус", "система", "состояние", "что с телефоном") -> SystemAgentCommand.STATUS
            text in setOf("возможности", "что ты можешь", "уровни доступа", "права") -> SystemAgentCommand.CAPABILITIES
            text in setOf("политика", "безопасность", "контроль") -> SystemAgentCommand.POLICY
            text in setOf("журнал", "что ты делала", "покажи журнал") -> SystemAgentCommand.LOG
            text in setOf("прошивка", "aelitaos", "rom", "лиза", "lisa") -> SystemAgentCommand.ROM
            text == "приложения" || text == "контроль приложений" || text.startsWith("открой ") -> SystemAgentCommand.APPS_PLACEHOLDER
            else -> SystemAgentCommand.UNKNOWN
        }
    }

    private fun statusResult(): SystemAgentResult {
        val decision = policyEngine.evaluate("SHOW_STATUS")
        actionLogStore.add("Запрошен статус системы", "status", decision.riskLabel)
        return result(getStatus().userMessage, "SHOW_STATUS", decision)
    }

    private fun capabilitiesResult(): SystemAgentResult {
        val decision = policyEngine.evaluate("SHOW_CAPABILITIES")
        actionLogStore.add("Запрошены возможности", "capabilities", decision.riskLabel)
        val message = capabilityRegistry.listCapabilities().joinToString(separator = "\n") {
            "${it.level}. ${it.title}: ${it.status}, риск: ${it.riskLabel}. ${it.description}"
        }
        return result("Текущие уровни доступа Аэлиты:\n$message", "SHOW_CAPABILITIES", decision)
    }

    private fun policyResult(): SystemAgentResult {
        val decision = policyEngine.evaluate("SHOW_CAPABILITIES")
        actionLogStore.add("Запрошена политика безопасности", "policy", decision.riskLabel)
        return result(
            "Политика прототипа: выполнять только локальные deterministic no-op действия и отчёты. " +
                "Привилегированные операции запрещены до будущей ROM/system-интеграции.",
            "SHOW_CAPABILITIES",
            decision,
        )
    }

    private fun logResult(): SystemAgentResult {
        val decision = policyEngine.evaluate("SHOW_LOG")
        actionLogStore.add("Запрошен журнал действий", "log", decision.riskLabel)
        val entries = actionLogStore.latest(20)
        val message = if (entries.isEmpty()) {
            "Журнал пока пуст."
        } else {
            entries.joinToString(separator = "\n") { "[${it.category}/${it.riskLabel}] ${it.message}" }
        }
        return result("Последние события:\n$message", "SHOW_LOG", decision)
    }

    private fun romResult(): SystemAgentResult {
        val decision = policyEngine.evaluate("SHOW_STATUS")
        actionLogStore.add("Запрошен статус AelitaOS/lisa ROM", "rom", decision.riskLabel)
        return result(
            "AelitaOS — целевой ROM-направление для Xiaomi 11 Lite 5G NE / lisa. " +
                "Сейчас ROM-интеграция не активна, ROM source tree отсутствует, buildability не заявляется.",
            "SHOW_STATUS",
            decision,
        )
    }

    private fun appControlPlaceholderResult(): SystemAgentResult {
        val decision = policyEngine.evaluate("PLACEHOLDER_APP_CONTROL")
        actionLogStore.add("Запрошен placeholder контроля приложений", "apps", decision.riskLabel)
        return result(
            "Контроль приложений пока работает только как архитектурный placeholder. " +
                "Реальный запуск приложений будет отдельным PR.",
            "PLACEHOLDER_APP_CONTROL",
            decision,
        )
    }

    private fun unknownResult(): SystemAgentResult {
        val decision = policyEngine.evaluate("UNKNOWN_COMMAND")
        actionLogStore.add("Неизвестная команда записана как no-op", "unknown", decision.riskLabel)
        return result(
            "Я пока не умею выполнять эту команду на системном уровне. Попытка записана в журнал.",
            "UNKNOWN_COMMAND",
            decision,
        )
    }

    private fun openSectionResult(message: String): SystemAgentResult {
        val decision = policyEngine.evaluate("OPEN_SECTION")
        return result(message, "OPEN_SECTION", decision)
    }

    private fun suggestionsResult(): SystemAgentResult = openSectionResult(
        "Следующий безопасный шаг: Local Core MVP внутри Shell.\n" +
            "Следующий системный шаг: Permission Center prototype.\n" +
            "ROM-шаг: исследовать lisa device tree, vendor blobs и rollback path.",
    )

    private fun result(message: String, actionName: String, decision: PolicyDecision): SystemAgentResult =
        SystemAgentResult(
            userMessage = if (decision.allowed) message else decision.reason,
            actionName = actionName,
            policyDecision = decision,
        )
}
