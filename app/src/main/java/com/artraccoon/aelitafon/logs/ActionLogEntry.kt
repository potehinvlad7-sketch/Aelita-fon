package com.artraccoon.aelitafon.logs

data class ActionLogEntry(
    val id: String,
    val createdAtMillis: Long,
    val message: String,
    val category: String,
    val riskLabel: String,
)
