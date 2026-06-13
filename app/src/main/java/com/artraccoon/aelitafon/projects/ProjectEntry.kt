package com.artraccoon.aelitafon.projects

data class ProjectEntry(
    val id: String,
    val title: String,
    val description: String?,
    val createdAtMillis: Long,
    val updatedAtMillis: Long,
    val status: String,
)
