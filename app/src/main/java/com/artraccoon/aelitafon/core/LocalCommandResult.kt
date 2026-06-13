package com.artraccoon.aelitafon.core

data class LocalCommandResult(
    val userMessage: String,
    val actionName: String,
    val riskLabel: String = "low",
)
