package com.artraccoon.aelitafon.permissions

interface PermissionCenter {
    fun getSnapshot(): PermissionCenterSnapshot
    fun getCapabilityDetails(id: String): PermissionCapability?
    fun getUserMessage(): String
}
