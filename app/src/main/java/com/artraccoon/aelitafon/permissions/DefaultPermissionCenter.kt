package com.artraccoon.aelitafon.permissions

class DefaultPermissionCenter : PermissionCenter {
    private val capabilities: List<PermissionCapability> = listOf(
        capability("SHELL_HOME", "Shell / домашний экран", "Аэлита может быть выбрана как HOME launcher и показывать собственную оболочку.", PermissionCapabilityLevel.SHELL, PermissionCapabilityStatus.ACTIVE, "low", "Работает через Android HOME intent-filter. Это не даёт системных привилегий."),
        capability("LOCAL_MEMORY", "Локальная память", "Аэлита может сохранять локальные воспоминания в памяти приложения.", PermissionCapabilityLevel.NORMAL_APP, PermissionCapabilityStatus.ACTIVE, "low", "SharedPreferences + org.json MVP. Позже миграция на Room."),
        capability("LOCAL_PROJECTS", "Локальные проекты", "Аэлита может хранить проекты локально.", PermissionCapabilityLevel.NORMAL_APP, PermissionCapabilityStatus.ACTIVE, "low", "SharedPreferences MVP."),
        capability("ACTION_LOG", "Журнал действий", "Аэлита записывает локальные действия и команды в журнал.", PermissionCapabilityLevel.NORMAL_APP, PermissionCapabilityStatus.ACTIVE, "low", "Persistent ActionLogStore, ограниченный MVP."),
        capability("APP_LIST", "Список приложений", "Аэлита может видеть launchable-приложения через обычные Android launcher intents.", PermissionCapabilityLevel.NORMAL_APP, PermissionCapabilityStatus.ACTIVE, "low", "Без QUERY_ALL_PACKAGES, только ACTION_MAIN + CATEGORY_LAUNCHER."),
        capability("APP_LAUNCH", "Запуск приложений", "Аэлита может запускать найденные приложения по явной команде пользователя.", PermissionCapabilityLevel.NORMAL_APP, PermissionCapabilityStatus.PARTIAL, "medium", "Обычный startActivity через launch intent. Это не управление приложением после запуска."),
        capability("APP_GOVERNANCE", "Глубокий контроль приложений", "Аэлита пока не может принудительно управлять жизненным циклом приложений, разрешениями, задачами или UI.", PermissionCapabilityLevel.ROM_FRAMEWORK, PermissionCapabilityStatus.LOCKED, "critical", "Потребуется priv-app, framework hooks или ROM integration. Не реализовано."),
        capability("NOTIFICATION_LISTENER", "Уведомления", "В будущем Аэлита сможет читать и суммаризировать уведомления только после явного включения пользователем.", PermissionCapabilityLevel.USER_GRANTED, PermissionCapabilityStatus.PLANNED, "medium", "NotificationListenerService не добавлен в этом PR."),
        capability("ACCESSIBILITY", "Accessibility-доступ", "Может дать управление UI, но это рискованный слой и требует отдельного проекта безопасности.", PermissionCapabilityLevel.USER_GRANTED, PermissionCapabilityStatus.LOCKED, "high", "Accessibility service не добавлен. Нельзя включать скрыто."),
        capability("USAGE_STATS", "Статистика использования", "В будущем может помочь понять привычки использования приложений.", PermissionCapabilityLevel.USER_GRANTED, PermissionCapabilityStatus.PLANNED, "medium", "PACKAGE_USAGE_STATS не добавлен."),
        capability("BACKGROUND_LIFE", "Фоновая жизнь", "Аэлита пока не работает в фоне как постоянный наблюдатель.", PermissionCapabilityLevel.NORMAL_APP, PermissionCapabilityStatus.PLANNED, "medium", "Нет foreground service, WorkManager или boot receiver."),
        capability("PRIV_APP", "Privileged system app", "Аэлита пока не установлена как priv-app и не имеет системных разрешений.", PermissionCapabilityLevel.PRIVILEGED_APP, PermissionCapabilityStatus.LOCKED, "high", "Потребуется ROM/system image integration."),
        capability("ROM_FRAMEWORK", "ROM/framework-интеграция", "Глубокая интеграция с AelitaOS пока не активна.", PermissionCapabilityLevel.ROM_FRAMEWORK, PermissionCapabilityStatus.LOCKED, "critical", "ROM tree отсутствует, buildability не заявляется."),
        capability("ROOT_KERNEL", "Root/kernel уровень", "Root/kernel действия запрещены в приложении и не должны выполняться скрыто.", PermissionCapabilityLevel.ROOT_KERNEL, PermissionCapabilityStatus.FORBIDDEN, "critical", "Никаких su/root/shell-команд."),
        capability("EXTERNAL_AI_CLOUD", "Внешний AI / cloud", "Аэлита не использует внешние AI API или cloud backend.", PermissionCapabilityLevel.NORMAL_APP, PermissionCapabilityStatus.FORBIDDEN, "high", "Нет INTERNET permission, нет API keys, нет telemetry."),
    )

    override fun getSnapshot(): PermissionCenterSnapshot {
        val activeCount = capabilities.count { it.status == PermissionCapabilityStatus.ACTIVE }
        val partialCount = capabilities.count { it.status == PermissionCapabilityStatus.PARTIAL }
        val lockedCount = capabilities.count { it.status == PermissionCapabilityStatus.LOCKED }
        val forbiddenCount = capabilities.count { it.status == PermissionCapabilityStatus.FORBIDDEN }
        return PermissionCenterSnapshot(
            capabilities = capabilities,
            activeCount = activeCount,
            partialCount = partialCount,
            lockedCount = lockedCount,
            forbiddenCount = forbiddenCount,
            userMessage = buildUserMessage(activeCount, partialCount, lockedCount, forbiddenCount),
        )
    }

    override fun getCapabilityDetails(id: String): PermissionCapability? =
        capabilities.firstOrNull { it.id.equals(id, ignoreCase = true) }

    override fun getUserMessage(): String = getSnapshot().userMessage

    private fun buildUserMessage(activeCount: Int, partialCount: Int, lockedCount: Int, forbiddenCount: Int): String {
        val rows = capabilities.joinToString("\n") {
            "• ${it.title}: ${it.status.name}, уровень: ${it.level.name}, риск: ${it.riskLabel}. ${it.description}"
        }
        return "Permission Center: честная карта возможностей Аэлиты.\n" +
            "Активно: $activeCount, частично: $partialCount, заблокировано: $lockedCount, запрещено: $forbiddenCount.\n" +
            "$rows\n\n" +
            "Этот экран только показывает возможности и границы. Он не запрашивает разрешения, не открывает настройки и не включает привилегированный доступ."
    }

    private fun capability(
        id: String,
        title: String,
        description: String,
        level: PermissionCapabilityLevel,
        status: PermissionCapabilityStatus,
        riskLabel: String,
        technicalNote: String,
        userActionLabel: String? = null,
    ) = PermissionCapability(id, title, description, level, status, riskLabel, userActionLabel, technicalNote)
}
