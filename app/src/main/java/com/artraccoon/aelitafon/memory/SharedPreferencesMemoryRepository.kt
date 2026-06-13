package com.artraccoon.aelitafon.memory

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID

class SharedPreferencesMemoryRepository(context: Context) : MemoryRepository {
    private val preferences = context.getSharedPreferences("aelita_memory", Context.MODE_PRIVATE)

    override fun addMemory(text: String): MemoryEntry {
        val entry = MemoryEntry(UUID.randomUUID().toString(), text.trim(), System.currentTimeMillis())
        save(getMemories() + entry)
        return entry
    }

    override fun getMemories(): List<MemoryEntry> = runCatching {
        val raw = preferences.getString(KEY, "[]") ?: "[]"
        val array = JSONArray(raw)
        List(array.length()) { index ->
            val item = array.getJSONObject(index)
            MemoryEntry(item.optString("id"), item.optString("text"), item.optLong("createdAtMillis"))
        }.filter { it.id.isNotBlank() && it.text.isNotBlank() }
    }.getOrElse {
        Log.w(TAG, "Malformed memory JSON, returning empty list", it)
        emptyList()
    }

    override fun deleteMemory(id: String): Boolean {
        val before = getMemories()
        val after = before.filterNot { it.id == id }
        if (after.size == before.size) return false
        save(after)
        return true
    }

    override fun clearMemories(): Boolean {
        preferences.edit().putString(KEY, "[]").apply()
        return true
    }

    private fun save(entries: List<MemoryEntry>) {
        val array = JSONArray()
        entries.forEach { entry ->
            array.put(JSONObject().put("id", entry.id).put("text", entry.text).put("createdAtMillis", entry.createdAtMillis))
        }
        preferences.edit().putString(KEY, array.toString()).apply()
    }

    private companion object { const val KEY = "entries"; const val TAG = "AelitaMemoryRepo" }
}
