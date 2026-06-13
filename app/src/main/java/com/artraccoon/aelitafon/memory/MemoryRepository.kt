package com.artraccoon.aelitafon.memory

interface MemoryRepository {
    fun addMemory(text: String): MemoryEntry
    fun getMemories(): List<MemoryEntry>
    fun deleteMemory(id: String): Boolean
    fun clearMemories(): Boolean
}
