package com.artraccoon.aelitafon.permissions

data class PermissionCapability(
    val id: String,
    val title: String,
    val description: String,
    val level: PermissionCapabilityLevel,
    val status: PermissionCapabilityStatus,
    val riskLabel: String,
    val userActionLabel: String?,
    val technicalNote: String,
)
