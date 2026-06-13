package com.artraccoon.aelitafon.policy

class DefaultPolicyEngine : PolicyEngine {
    override fun evaluate(actionName: String): PolicyDecision = when (actionName) {
        "SHOW_STATUS" -> allowed("low", "Показ локального статуса безопасен.")
        "SHOW_CAPABILITIES" -> allowed("low", "Показ уровней доступа безопасен.")
        "SHOW_LOG" -> allowed("low", "Показ in-memory журнала безопасен.")
        "OPEN_SECTION" -> allowed("low", "Открытие раздела Shell является локальным no-op.")
        "PLACEHOLDER_APP_CONTROL" -> allowed("medium", "Контроль приложений разрешён только как архитектурный placeholder без запуска приложений.")
        "UNKNOWN_COMMAND" -> allowed("low", "Неизвестная команда записывается как безопасный no-op.")
        else -> PolicyDecision(
            allowed = false,
            riskLabel = "high",
            reason = "Это действие требует будущей ROM/system-интеграции и сейчас недоступно.",
            requiresUserConfirmation = false,
        )
    }

    private fun allowed(riskLabel: String, reason: String) = PolicyDecision(
        allowed = true,
        riskLabel = riskLabel,
        reason = reason,
        requiresUserConfirmation = false,
    )
}
