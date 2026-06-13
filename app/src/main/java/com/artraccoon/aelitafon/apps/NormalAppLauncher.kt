package com.artraccoon.aelitafon.apps

import android.content.Context
import android.content.Intent

class NormalAppLauncher(context: Context) : AppLauncher {
    private val appContext = context.applicationContext
    private val packageManager = appContext.packageManager

    override fun launchApp(packageName: String): Boolean = runCatching {
        val intent = packageManager.getLaunchIntentForPackage(packageName) ?: return false
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        appContext.startActivity(intent)
        true
    }.getOrDefault(false)
}
