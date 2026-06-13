package com.artraccoon.aelitafon.policy

data class PolicyDecision(
    val allowed: Boolean,
    val riskLabel: String,
    val reason: String,
    val requiresUserConfirmation: Boolean,
)
