package com.artraccoon.aelitafon.device

import android.content.Context

class NormalAppDeviceStateReader(
    context: Context,
) : DeviceStateReader {
    private val appContext = context.applicationContext

    override fun readSnapshot(): DeviceStateSnapshot = DeviceStateSnapshot(
        capturedAtMillis = System.currentTimeMillis(),
        packageName = appContext.packageName,
        appMode = "normal-app shell prototype",
        localOnly = true,
        privilegedAccess = false,
        notificationAccess = false,
        accessibilityAccess = false,
        rootAccess = false,
        romBridge = false,
    )
}
