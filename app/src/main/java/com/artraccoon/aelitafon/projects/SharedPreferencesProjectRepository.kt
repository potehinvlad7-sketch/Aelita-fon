package com.artraccoon.aelitafon.projects

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID

class SharedPreferencesProjectRepository(context: Context) : ProjectRepository {
    private val preferences = context.getSharedPreferences("aelita_projects", Context.MODE_PRIVATE)

    override fun addProject(title: String): ProjectEntry {
        val now = System.currentTimeMillis()
        val entry = ProjectEntry(UUID.randomUUID().toString(), title.trim(), null, now, now, "active")
        save(getProjects() + entry)
        return entry
    }

    override fun getProjects(): List<ProjectEntry> = runCatching {
        val raw = preferences.getString(KEY, "[]") ?: "[]"
        val array = JSONArray(raw)
        List(array.length()) { index ->
            val item = array.getJSONObject(index)
            ProjectEntry(
                id = item.optString("id"),
                title = item.optString("title"),
                description = item.optString("description").ifBlank { null },
                createdAtMillis = item.optLong("createdAtMillis"),
                updatedAtMillis = item.optLong("updatedAtMillis"),
                status = item.optString("status", "active"),
            )
        }.filter { it.id.isNotBlank() && it.title.isNotBlank() }
    }.getOrElse {
        Log.w(TAG, "Malformed project JSON, returning empty list", it)
        emptyList()
    }

    override fun deleteProject(id: String): Boolean {
        val before = getProjects()
        val after = before.filterNot { it.id == id }
        if (after.size == before.size) return false
        save(after)
        return true
    }

    override fun clearProjects(): Boolean {
        preferences.edit().putString(KEY, "[]").apply()
        return true
    }

    private fun save(entries: List<ProjectEntry>) {
        val array = JSONArray()
        entries.forEach { entry ->
            array.put(JSONObject()
                .put("id", entry.id).put("title", entry.title).put("description", entry.description)
                .put("createdAtMillis", entry.createdAtMillis).put("updatedAtMillis", entry.updatedAtMillis).put("status", entry.status))
        }
        preferences.edit().putString(KEY, array.toString()).apply()
    }

    private companion object { const val KEY = "entries"; const val TAG = "AelitaProjectRepo" }
}
