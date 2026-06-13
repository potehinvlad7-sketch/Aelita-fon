package com.artraccoon.aelitafon.systemagent

import com.artraccoon.aelitafon.policy.PolicyDecision

data class SystemAgentResult(
    val userMessage: String,
    val actionName: String,
    val policyDecision: PolicyDecision,
)
