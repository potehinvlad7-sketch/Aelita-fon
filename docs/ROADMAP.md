# Aelita-Fon Roadmap

## Phase 0. Repository and documentation

Цель: создать документационную основу проекта без Android-кода.

- README.md.
- SPEC.md.
- ROADMAP.md.
- CODEX_RULES.md.
- ARCHITECTURE.md.
- Зафиксировать offline-first и local-only ограничения.

## Phase 1. Android skeleton

Цель: добавить минимальный Android-проект без бизнес-логики.

- Настроить структуру проекта.
- Добавить базовую Activity.
- Добавить минимальную тему.
- Не добавлять внешние AI API.

## Phase 2. Launcher mode

Статус: **implemented as a minimal prototype**.

Цель: подготовить Aelita-Fon как launcher shell.

- Добавить launcher intent filters.
- Проверить поведение Home.
- Описать ограничения Android launcher mode.
- Launcher mode does not give root or system access.
- Launcher mode only replaces the Android home screen behavior.

## Phase 3. Shell UI

Цель: создать Russian-first главный экран Aelita.

- Командная строка.
- Карточки статуса.
- Быстрые действия.
- Состояние offline/local-only.

## Phase 4. Room database

Цель: добавить локальное хранилище.

- Room schema.
- Миграционная стратегия.
- Локальные DAO.
- Без cloud sync.

## Phase 5. Memory

Цель: реализовать локальную память Aelita.

- Создание, просмотр, редактирование и удаление памяти.
- Теги и типы памяти.
- Связи с проектами и задачами.

## Phase 6. Projects

Цель: добавить проекты и задачи.

- Project model.
- Task model.
- Статусы и приоритеты.
- Привязка команд к проектам.

## Phase 7. App launcher

Цель: добавить безопасный запуск приложений.

- Получение списка приложений.
- Запуск через Android intents.
- Логирование запусков.

## Phase 8. Action Log

Цель: сделать журнал действий обязательной частью системы.

- Записи действий.
- Фильтры.
- Детали записи.
- Связь с разрешениями и Device Bridge.

## Phase 9. Background Life

Цель: подготовить видимый и отключаемый фоновый режим.

- Foreground service strategy.
- Ограничения энергосбережения.
- Видимый статус.
- Полное логирование действий.

## Phase 10. Permission Center

Цель: создать центр разрешений и возможностей.

- Список capabilities.
- Объяснения рисков.
- Статусы доступов.
- Инструкции включения и отключения.

## Phase 11. Device Bridge placeholders

Цель: добавить безопасные заглушки для будущих уровней доступа.

- Accessibility placeholder.
- Notification Listener placeholder.
- Usage Access placeholder.
- Storage Access Framework placeholder.
- Shizuku placeholder.
- Root placeholder.
- System app placeholder.
- Custom ROM placeholder.

## Phase 12. Local Mind abstraction

Цель: подготовить интерфейс локального интеллекта.

- `LocalMind` contract.
- `RuleBasedMind` для MVP.
- `MockMind` для тестирования UI.
- План будущей локальной LLM-интеграции.

## Phase 13. Evolution Engine

Цель: добавить механизм предложений по развитию Aelita.

- Анализ локальных паттернов.
- Видимые предложения.
- Подтверждение пользователя.
- Запрет silent self-modification.
- Логирование изменений.

## Phase 14. Polish and docs

Цель: стабилизировать MVP и расширить документацию.

- UX polish.
- Обновление SPEC.
- Документация ограничений.
- Руководство локальной сборки.
- Подготовка следующих задач.
