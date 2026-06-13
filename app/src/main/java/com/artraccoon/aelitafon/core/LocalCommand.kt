package com.artraccoon.aelitafon.core

sealed class LocalCommand {
    data class AddMemory(val text: String) : LocalCommand()
    data object ShowMemory : LocalCommand()
    data object ClearMemoryRequested : LocalCommand()
    data class AddProject(val title: String) : LocalCommand()
    data object ShowProjects : LocalCommand()
    data object ClearProjectsRequested : LocalCommand()
    data object ShowJournal : LocalCommand()
    data object ShowSuggestions : LocalCommand()
    data object ShowStatus : LocalCommand()
    data object ShowCapabilities : LocalCommand()
    data object ListApps : LocalCommand()
    data class SearchApp(val query: String) : LocalCommand()
    data class LaunchApp(val query: String) : LocalCommand()
    data object RomInfo : LocalCommand()
    data object Help : LocalCommand()
    data object Unknown : LocalCommand()
}
