package com.artraccoon.aelitafon.logs

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID

class SharedPreferencesActionLogStore(context: Context) : ActionLogStore {
    private val preferences = context.getSharedPreferences("aelita_action_log", Context.MODE_PRIVATE)

    override fun add(message: String, category: String, riskLabel: String): ActionLogEntry {
        val entry = ActionLogEntry(UUID.randomUUID().toString(), System.currentTimeMillis(), message, category, riskLabel)
        save((listOf(entry) + readAll()).take(MAX_ENTRIES))
        return entry
    }

    override fun latest(limit: Int): List<ActionLogEntry> = readAll().take(limit.coerceAtLeast(0))

    override fun clear(): Boolean {
        preferences.edit().putString(KEY, "[]").apply()
        return true
    }

    private fun readAll(): List<ActionLogEntry> = runCatching {
        val raw = preferences.getString(KEY, "[]") ?: "[]"
        val array = JSONArray(raw)
        List(array.length()) { index ->
            val item = array.getJSONObject(index)
            ActionLogEntry(
                id = item.optString("id"),
                createdAtMillis = item.optLong("createdAtMillis"),
                message = item.optString("message"),
                category = item.optString("category"),
                riskLabel = item.optString("riskLabel", "low"),
            )
        }.filter { it.id.isNotBlank() && it.message.isNotBlank() }
    }.getOrElse {
        Log.w(TAG, "Malformed action log JSON, returning empty list", it)
        emptyList()
    }

    private fun save(entries: List<ActionLogEntry>) {
        val array = JSONArray()
        entries.forEach { entry ->
            array.put(JSONObject().put("id", entry.id).put("createdAtMillis", entry.createdAtMillis)
                .put("message", entry.message).put("category", entry.category).put("riskLabel", entry.riskLabel))
        }
        preferences.edit().putString(KEY, array.toString()).apply()
    }

    private companion object { const val KEY = "entries"; const val TAG = "AelitaActionLog"; const val MAX_ENTRIES = 200 }
}
