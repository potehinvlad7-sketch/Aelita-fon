package com.artraccoon.aelitafon.apps

interface AppLauncher {
    fun launchApp(packageName: String): Boolean
    fun launchApp(entry: AppEntry): Boolean = launchApp(entry.packageName)
}
