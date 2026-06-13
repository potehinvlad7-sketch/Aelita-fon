package com.artraccoon.aelitafon.apps

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build

class NormalAppRepository(context: Context) : AppRepository {
    private val packageManager = context.packageManager

    override fun getLaunchableApps(): List<AppEntry> {
        val intent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER)
        val activities = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.queryIntentActivities(intent, PackageManager.ResolveInfoFlags.of(0))
        } else {
            @Suppress("DEPRECATION")
            packageManager.queryIntentActivities(intent, 0)
        }
        return activities.mapNotNull { info ->
            val activity = info.activityInfo ?: return@mapNotNull null
            val label = info.loadLabel(packageManager)?.toString()?.trim().orEmpty()
                .ifBlank { activity.packageName }
            AppEntry(
                packageName = activity.packageName,
                label = label,
                activityName = activity.name,
                isLaunchable = true,
            )
        }.distinctBy { it.packageName to it.activityName }
            .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.label })
    }

    override fun searchApps(query: String): AppSearchResult {
        val normalized = query.trim().lowercase()
        if (normalized.isBlank()) {
            return AppSearchResult(query, emptyList(), "Укажи название приложения для поиска.")
        }
        val apps = getLaunchableApps()
        val matches = apps.filter { it.label.lowercase() == normalized || it.packageName.lowercase() == normalized } +
            apps.filter { it.label.lowercase() != normalized && it.packageName.lowercase() != normalized && (it.label.lowercase().contains(normalized) || it.packageName.lowercase().contains(normalized)) }
        val unique = matches.distinctBy { it.packageName to it.activityName }.take(10)
        val message = if (unique.isEmpty()) {
            "Не нашла launchable-приложение: ${query.trim()}"
        } else {
            "Нашла приложения:\n" + unique.joinToString("\n") { "• ${it.label} (${it.packageName})" }
        }
        return AppSearchResult(query.trim(), unique, message)
    }
}
