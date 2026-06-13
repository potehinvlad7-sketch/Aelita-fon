package com.artraccoon.aelitafon.core

class LocalCommandParser {
    fun parse(input: String): LocalCommand {
        val text = input.trim().lowercase()
        return when {
            text.startsWith("запомни ") -> LocalCommand.AddMemory(text.removePrefix("запомни ").trim())
            text == "запомни" || text == "запомнить" -> LocalCommand.AddMemory("")
            text.startsWith("запомнить ") -> LocalCommand.AddMemory(text.removePrefix("запомнить ").trim())
            text in setOf("память", "покажи память", "что ты помнишь") -> LocalCommand.ShowMemory
            text == "очисти память" -> LocalCommand.ClearMemoryRequested
            text.startsWith("добавь проект ") -> LocalCommand.AddProject(text.removePrefix("добавь проект ").trim())
            text.startsWith("создай проект ") -> LocalCommand.AddProject(text.removePrefix("создай проект ").trim())
            text.startsWith("новый проект ") -> LocalCommand.AddProject(text.removePrefix("новый проект ").trim())
            text in setOf("добавь проект", "создай проект", "новый проект") -> LocalCommand.AddProject("")
            text in setOf("проекты", "покажи проекты") -> LocalCommand.ShowProjects
            text == "очисти проекты" -> LocalCommand.ClearProjectsRequested
            text in setOf("журнал", "покажи журнал", "что ты делала") -> LocalCommand.ShowJournal
            text in setOf("предложения", "идеи", "что ты предлагаешь") -> LocalCommand.ShowSuggestions
            text in setOf("статус", "система", "состояние") -> LocalCommand.ShowStatus
            text in setOf("возможности", "что ты можешь", "права", "уровни доступа") -> LocalCommand.ShowCapabilities
            text == "приложения" || text.startsWith("открой ") || text.startsWith("запусти ") -> LocalCommand.AppsPlaceholder
            text in setOf("прошивка", "rom", "aelitaos", "lisa", "лиза") -> LocalCommand.RomInfo
            text in setOf("помощь", "команды", "что умеешь") -> LocalCommand.Help
            else -> LocalCommand.Unknown
        }
    }
}
