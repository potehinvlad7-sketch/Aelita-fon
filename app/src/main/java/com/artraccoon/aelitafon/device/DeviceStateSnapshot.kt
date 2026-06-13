package com.artraccoon.aelitafon.device

data class DeviceStateSnapshot(
    val capturedAtMillis: Long,
    val packageName: String,
    val appMode: String,
    val localOnly: Boolean,
    val privilegedAccess: Boolean,
    val notificationAccess: Boolean,
    val accessibilityAccess: Boolean,
    val rootAccess: Boolean,
    val romBridge: Boolean,
)
