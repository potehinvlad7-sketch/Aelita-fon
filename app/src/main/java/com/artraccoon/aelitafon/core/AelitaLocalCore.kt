package com.artraccoon.aelitafon.core

import com.artraccoon.aelitafon.evolution.LocalSuggestion
import com.artraccoon.aelitafon.logs.ActionLogEntry
import com.artraccoon.aelitafon.memory.MemoryEntry
import com.artraccoon.aelitafon.projects.ProjectEntry

interface AelitaLocalCore {
    fun handleInput(input: String): LocalCommandResult
    fun getSummary(): LocalCoreSummary
    fun getMemories(): List<MemoryEntry>
    fun getProjects(): List<ProjectEntry>
    fun getSuggestions(): List<LocalSuggestion>
    fun getActionLog(limit: Int = 30): List<ActionLogEntry>
}
