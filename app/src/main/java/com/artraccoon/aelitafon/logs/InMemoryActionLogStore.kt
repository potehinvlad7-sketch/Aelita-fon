package com.artraccoon.aelitafon.logs

import java.util.UUID

class InMemoryActionLogStore : ActionLogStore {
    private val entries = mutableListOf<ActionLogEntry>()

    override fun add(message: String, category: String, riskLabel: String): ActionLogEntry {
        val entry = ActionLogEntry(
            id = UUID.randomUUID().toString(),
            createdAtMillis = System.currentTimeMillis(),
            message = message,
            category = category,
            riskLabel = riskLabel,
        )
        entries.add(0, entry)
        return entry
    }

    override fun latest(limit: Int): List<ActionLogEntry> = entries.take(limit.coerceAtLeast(0))

    override fun clear(): Boolean {
        entries.clear()
        return true
    }
}
