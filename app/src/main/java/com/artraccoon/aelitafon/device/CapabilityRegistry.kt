package com.artraccoon.aelitafon.device

class CapabilityRegistry {
    fun listCapabilities(): List<Capability> = listOf(
        Capability(
            id = "SHELL_CONTROL",
            title = "Shell control",
            level = 1,
            status = "active prototype",
            riskLabel = "low",
            description = "Локальная Shell-оболочка принимает команды и показывает безопасные ответы.",
        ),
        Capability(
            id = "NORMAL_APP_APIS",
            title = "Normal app APIs",
            level = 2,
            status = "partial / planned",
            riskLabel = "low",
            description = "Обычные Android API без опасных разрешений; реальные интеграции будут добавляться отдельно.",
        ),
        Capability(
            id = "USER_GRANTED_ACCESS",
            title = "User-granted access",
            level = 3,
            status = "not active",
            riskLabel = "medium",
            description = "Доступ, явно выданный пользователем, сейчас не подключён.",
        ),
        Capability(
            id = "PRIVILEGED_SYSTEM_APP",
            title = "Privileged system app",
            level = 4,
            status = "not active",
            riskLabel = "high",
            description = "Системные привилегии недоступны в normal-app прототипе.",
        ),
        Capability(
            id = "ROM_FRAMEWORK_INTEGRATION",
            title = "ROM framework integration",
            level = 5,
            status = "not active",
            riskLabel = "critical",
            description = "Мост с AelitaOS ROM пока не существует.",
        ),
        Capability(
            id = "ROOT_KERNEL_LOW_LEVEL",
            title = "Root / kernel low level",
            level = 6,
            status = "not active",
            riskLabel = "critical",
            description = "Root, kernel и низкоуровневое управление не используются.",
        ),
    )
}
