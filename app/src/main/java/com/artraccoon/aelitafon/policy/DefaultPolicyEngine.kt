package com.artraccoon.aelitafon.policy

class DefaultPolicyEngine : PolicyEngine {
    override fun evaluate(actionName: String): PolicyDecision = when (actionName) {
        "SHOW_STATUS" -> allowed("low", "Показ локального статуса безопасен.")
        "SHOW_CAPABILITIES" -> allowed("low", "Показ уровней доступа безопасен.")
        "SHOW_LOG" -> allowed("low", "Показ in-memory журнала безопасен.")
        "OPEN_SECTION" -> allowed("low", "Открытие раздела Shell является локальным no-op.")
        "LIST_APPS" -> allowed("low", "Показ launchable-приложений через обычные Android API.")
        "SEARCH_APPS" -> allowed("low", "Локальный поиск по списку launchable-приложений.")
        "LAUNCH_APP" -> allowed("medium", "Запуск приложения выполняется только по явной команде пользователя через обычный Android Intent.")
        "LAUNCH_APP_FAILED" -> allowed("low", "Не удалось запустить приложение; действие ограничено обычными Android API.")
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
