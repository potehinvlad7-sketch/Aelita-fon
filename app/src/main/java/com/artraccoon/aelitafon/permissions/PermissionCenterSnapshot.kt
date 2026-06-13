package com.artraccoon.aelitafon.permissions

data class PermissionCenterSnapshot(
    val capabilities: List<PermissionCapability>,
    val activeCount: Int,
    val partialCount: Int,
    val lockedCount: Int,
    val forbiddenCount: Int,
    val userMessage: String,
)
