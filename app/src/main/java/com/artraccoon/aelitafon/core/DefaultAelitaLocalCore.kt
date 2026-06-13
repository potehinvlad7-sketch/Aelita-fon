package com.artraccoon.aelitafon.core

import com.artraccoon.aelitafon.evolution.LocalSuggestion
import com.artraccoon.aelitafon.evolution.LocalSuggestionEngine
import com.artraccoon.aelitafon.logs.ActionLogEntry
import com.artraccoon.aelitafon.logs.ActionLogStore
import com.artraccoon.aelitafon.memory.MemoryEntry
import com.artraccoon.aelitafon.memory.MemoryRepository
import com.artraccoon.aelitafon.projects.ProjectEntry
import com.artraccoon.aelitafon.projects.ProjectRepository

class DefaultAelitaLocalCore(
    private val memoryRepository: MemoryRepository,
    private val projectRepository: ProjectRepository,
    private val actionLogStore: ActionLogStore,
    private val suggestionEngine: LocalSuggestionEngine,
    private val parser: LocalCommandParser = LocalCommandParser(),
) : AelitaLocalCore {
    override fun handleInput(input: String): LocalCommandResult = when (val command = parser.parse(input)) {
        is LocalCommand.AddMemory -> addMemory(command.text)
        LocalCommand.ShowMemory -> showMemories()
        LocalCommand.ClearMemoryRequested -> safeMessage("Очистка памяти будет добавлена позже с подтверждением.", "memory_clear_requested", "memory")
        is LocalCommand.AddProject -> addProject(command.title)
        LocalCommand.ShowProjects -> showProjects()
        LocalCommand.ClearProjectsRequested -> safeMessage("Очистка проектов будет добавлена позже с подтверждением.", "projects_clear_requested", "projects")
        LocalCommand.ShowJournal -> showJournal()
        LocalCommand.ShowSuggestions -> showSuggestions()
        LocalCommand.ShowStatus -> safeMessage(getSummary().userMessage, "local_status", "status")
        LocalCommand.ShowCapabilities -> safeMessage(capabilitiesText(), "local_capabilities", "capabilities")
        LocalCommand.ListApps -> safeMessage("Приложения доступны через System Agent: обычный Android-запуск, не ROM-контроль.", "LIST_APPS", "apps")
        is LocalCommand.SearchApp -> safeMessage("Поиск приложений выполняет System Agent: ${command.query}", "SEARCH_APPS", "apps")
        is LocalCommand.LaunchApp -> safeMessage("Запуск приложений выполняет System Agent только по явной команде: ${command.query}", "LAUNCH_APP", "apps")
        LocalCommand.RomInfo -> safeMessage("ROM-интеграция не активна. Перед любыми прошивочными действиями нужен отдельный анализ lisa device tree, vendor blobs и rollback path.", "rom_info", "rom")
        LocalCommand.Help -> safeMessage(helpText(), "help", "help")
        LocalCommand.Unknown -> safeMessage("Я пока не умею выполнять эту команду. Попытка записана в журнал.", "unknown", "unknown")
    }

    override fun getSummary(): LocalCoreSummary {
        val memories = getMemories().size
        val projects = getProjects().size
        val logs = getActionLog(200).size
        return LocalCoreSummary(memories, projects, logs, "Local Core активен: память — $memories, проекты — $projects, журнал — $logs. Всё хранится локально, без сети и внешних AI API.")
    }

    override fun getMemories(): List<MemoryEntry> = memoryRepository.getMemories()
    override fun getProjects(): List<ProjectEntry> = projectRepository.getProjects()
    override fun getSuggestions(): List<LocalSuggestion> = suggestionEngine.generate(getMemories(), getProjects(), getActionLog(200))
    override fun getActionLog(limit: Int): List<ActionLogEntry> = actionLogStore.latest(limit)

    private fun addMemory(text: String): LocalCommandResult {
        if (text.isBlank()) return safeMessage("Нечего запоминать. Напиши: запомни <текст>", "memory_empty", "memory")
        val entry = memoryRepository.addMemory(text)
        actionLogStore.add("Сохранено воспоминание: ${entry.text}", "memory", "low")
        return LocalCommandResult("Запомнила: ${entry.text}", "memory_add")
    }

    private fun addProject(title: String): LocalCommandResult {
        if (title.isBlank()) return safeMessage("Название проекта пустое. Напиши: добавь проект <название>", "project_empty", "projects")
        val entry = projectRepository.addProject(title)
        actionLogStore.add("Создан проект: ${entry.title}", "projects", "low")
        return LocalCommandResult("Проект создан: ${entry.title}", "project_add")
    }

    private fun showMemories(): LocalCommandResult = safeMessage(
        if (getMemories().isEmpty()) "Память пока пуста. Напиши: запомни <важный факт>" else getMemories().joinToString("\n") { "• ${it.text}" },
        "memory_show",
        "memory",
    )

    private fun showProjects(): LocalCommandResult = safeMessage(
        if (getProjects().isEmpty()) "Проекты пока не созданы. Напиши: добавь проект <название>" else getProjects().joinToString("\n") { "• ${it.title} — ${it.status}" },
        "projects_show",
        "projects",
    )

    private fun showJournal(): LocalCommandResult = safeMessage(
        if (getActionLog(20).isEmpty()) "Журнал пока пуст." else getActionLog(20).joinToString("\n") { "[${it.category}/${it.riskLabel}] ${it.message}" },
        "journal_show",
        "log",
    )

    private fun showSuggestions(): LocalCommandResult = safeMessage(getSuggestions().joinToString("\n") { "• ${it.title}: ${it.text}" }, "suggestions_show", "suggestions")

    private fun safeMessage(message: String, actionName: String, category: String): LocalCommandResult {
        actionLogStore.add(message, category, "low")
        return LocalCommandResult(message, actionName)
    }

    private fun capabilitiesText() = "Я умею локально: запоминать факты, вести проекты, показывать журнал, предложения и статус. Я запускаю приложения только по явной команде через обычные Android Intent; не имею root/Accessibility/Notification Listener, не использую сеть, облако или внешние AI API."

    private fun helpText() = "Команды: запомни <текст>, память, добавь проект <название>, проекты, журнал, предложения, статус, возможности, приложения, найди приложение <название>, открой <название>, прошивка. Очистка памяти и проектов появится позже только с подтверждением."
}
