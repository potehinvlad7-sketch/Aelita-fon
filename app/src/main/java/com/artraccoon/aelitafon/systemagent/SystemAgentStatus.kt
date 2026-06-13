package com.artraccoon.aelitafon.systemagent

data class SystemAgentStatus(
    val appMode: String,
    val romMode: String,
    val launcherModeKnown: String,
    val privilegedMode: String,
    val backgroundMode: String,
    val localOnly: Boolean,
    val externalAiApiEnabled: Boolean = false,
    val cloudBackendEnabled: Boolean = false,
    val userMessage: String,
)
