package com.artraccoon.aelitafon.systemagent

enum class SystemAgentCommand(val actionName: String) {
    STATUS("SHOW_STATUS"),
    CAPABILITIES("SHOW_CAPABILITIES"),
    POLICY("SHOW_CAPABILITIES"),
    LOG("SHOW_LOG"),
    ROM("SHOW_STATUS"),
    LIST_APPS("LIST_APPS"),
    SEARCH_APPS("SEARCH_APPS"),
    LAUNCH_APP("LAUNCH_APP"),
    LAUNCH_APP_FAILED("LAUNCH_APP_FAILED"),
    UNKNOWN("UNKNOWN_COMMAND"),
}
