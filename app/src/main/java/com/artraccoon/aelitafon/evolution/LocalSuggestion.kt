package com.artraccoon.aelitafon.evolution

data class LocalSuggestion(
    val id: String,
    val title: String,
    val text: String,
    val riskLabel: String,
    val createdAtMillis: Long,
)
