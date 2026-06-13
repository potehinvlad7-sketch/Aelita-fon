package com.artraccoon.aelitafon.apps

data class AppEntry(
    val packageName: String,
    val label: String,
    val activityName: String?,
    val isLaunchable: Boolean,
)
