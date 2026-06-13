package com.artraccoon.aelitafon.shell

data class ShellState(
    val commandText: String = "",
    val localStatus: String = "Готова к локальной команде",
    val systemNodes: List<SystemNode> = defaultSystemNodes,
)

data class SystemNode(
    val title: String,
    val subtitle: String,
)

val defaultSystemNodes: List<SystemNode> = listOf(
    SystemNode(
        title = "Память",
        subtitle = "локальные воспоминания",
    ),
    SystemNode(
        title = "Проекты",
        subtitle = "активные направления",
    ),
    SystemNode(
        title = "Приложения",
        subtitle = "запуск и поиск",
    ),
    SystemNode(
        title = "Система",
        subtitle = "состояние устройства",
    ),
    SystemNode(
        title = "Журнал",
        subtitle = "что делала Аэлита",
    ),
    SystemNode(
        title = "Предложения",
        subtitle = "идеи и улучшения",
    ),
)
