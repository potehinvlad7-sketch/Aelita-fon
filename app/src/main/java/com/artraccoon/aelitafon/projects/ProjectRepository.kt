package com.artraccoon.aelitafon.projects

interface ProjectRepository {
    fun addProject(title: String): ProjectEntry
    fun getProjects(): List<ProjectEntry>
    fun deleteProject(id: String): Boolean
    fun clearProjects(): Boolean
}
