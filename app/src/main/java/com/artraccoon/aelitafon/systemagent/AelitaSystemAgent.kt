package com.artraccoon.aelitafon.systemagent

import com.artraccoon.aelitafon.logs.ActionLogEntry

interface AelitaSystemAgent {
    fun getStatus(): SystemAgentStatus
    fun handleCommand(input: String): SystemAgentResult
    fun openSection(sectionName: String): SystemAgentResult
    fun getActionLog(limit: Int = 20): List<ActionLogEntry>
}
