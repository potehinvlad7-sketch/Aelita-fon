package com.artraccoon.aelitafon.policy

interface PolicyEngine {
    fun evaluate(actionName: String): PolicyDecision
}
