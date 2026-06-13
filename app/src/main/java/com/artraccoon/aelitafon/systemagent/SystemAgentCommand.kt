package com.artraccoon.aelitafon.systemagent

enum class SystemAgentCommand(val actionName: String) {
    STATUS("SHOW_STATUS"),
    CAPABILITIES("SHOW_CAPABILITIES"),
    POLICY("SHOW_CAPABILITIES"),
    LOG("SHOW_LOG"),
    ROM("SHOW_STATUS"),
    APPS_PLACEHOLDER("PLACEHOLDER_APP_CONTROL"),
    UNKNOWN("UNKNOWN_COMMAND"),
}
