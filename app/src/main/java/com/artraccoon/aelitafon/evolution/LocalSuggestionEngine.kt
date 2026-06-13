package com.artraccoon.aelitafon.evolution

import com.artraccoon.aelitafon.logs.ActionLogEntry
import com.artraccoon.aelitafon.memory.MemoryEntry
import com.artraccoon.aelitafon.projects.ProjectEntry

class LocalSuggestionEngine {
    fun generate(
        memories: List<MemoryEntry>,
        projects: List<ProjectEntry>,
        logs: List<ActionLogEntry>,
    ): List<LocalSuggestion> {
        val now = System.currentTimeMillis()
        val suggestions = mutableListOf<LocalSuggestion>()
        if (memories.isEmpty()) suggestions += suggestion("memory-first", "Добавь первое воспоминание", "Напиши: запомни <важный факт>", now)
        if (projects.isEmpty()) {
            suggestions += suggestion("project-first", "Создай первый проект", "Напиши: добавь проект <название>", now)
        } else {
            suggestions += suggestion("projects-exist", "Проекты уже есть", "Можно открыть раздел Проекты и продолжить планирование.", now)
        }
        if (logs.size >= 20) suggestions += suggestion("log-filters", "Журнал наполняется", "Позже стоит добавить фильтры и экспорт журнала.", now)
        suggestions += suggestion("permission-center", "Следующий системный шаг", "Permission Center покажет, какие возможности Аэлиты активны, а какие ещё ждут ROM-интеграции.", now)
        suggestions += suggestion("rom-research", "Следующий ROM-шаг", "Исследовать lisa device tree, vendor blobs и rollback path перед любыми прошивочными действиями.", now)
        return suggestions
    }

    private fun suggestion(id: String, title: String, text: String, now: Long) = LocalSuggestion(id, title, text, "low", now)
}
