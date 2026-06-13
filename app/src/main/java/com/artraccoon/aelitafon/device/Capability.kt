package com.artraccoon.aelitafon.device

data class Capability(
    val id: String,
    val title: String,
    val level: Int,
    val status: String,
    val riskLabel: String,
    val description: String,
)
