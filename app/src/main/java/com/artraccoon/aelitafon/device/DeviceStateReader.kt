package com.artraccoon.aelitafon.device

interface DeviceStateReader {
    fun readSnapshot(): DeviceStateSnapshot
}
