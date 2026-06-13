package com.artraccoon.aelitafon.logs

interface ActionLogStore {
    fun add(message: String, category: String, riskLabel: String): ActionLogEntry
    fun latest(limit: Int): List<ActionLogEntry>
    fun clear(): Boolean
}
