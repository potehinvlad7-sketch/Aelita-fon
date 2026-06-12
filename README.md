# Aelita-Fon

**Aelita-Fon** — offline-first AI-native оболочка для Android-телефона, где Android остаётся технической основой, а Aelita становится основным локальным интерфейсом системы: памятью, планировщиком, наблюдателем и слоем управления.

> ⚠️ В проекте не используются внешние AI API. Архитектура проектируется как локальная и автономная.

## Что это за проект

Aelita-Fon — это экспериментальная локальная среда телефона, в которой пользователь взаимодействует с устройством через Aelita как через главный системный слой. Цель проекта — постепенно заменить разрозненные экраны, ярлыки и ручные действия единым локальным интерфейсом, который понимает контекст, ведёт память, планирует действия и безопасно управляет доступными возможностями устройства.

Android в этой модели остаётся техническим субстратом: системой разрешений, приложений, сервисов, уведомлений, хранилища и жизненного цикла. Aelita становится пользовательской оболочкой поверх этого субстрата.

## Чем проект не является

- Aelita-Fon — **не chatbot app**.
- Aelita-Fon — **не обычный launcher**.
- Aelita-Fon — **не облачный ассистент**.
- Aelita-Fon — **не клиент внешнего LLM API**.
- Aelita-Fon — **не система скрытой автоматизации**.
- Aelita-Fon — **не попытка сразу получить root или системные привилегии**.

## Core principles

- Offline-first.
- Local-only architecture.
- No external AI APIs.
- No cloud backend.
- No cloud sync.
- Designed for local LLM integration later.
- Designed for background operation.
- Every autonomous action must be logged.
- Aelita may suggest updates, but must not silently modify herself.
- UI должен оставаться Russian-first.

## Planned stack

Планируемый стек будет уточняться в отдельных задачах. На уровне документации предполагается:

- Android native shell.
- Kotlin для Android-кода в будущих этапах.
- Jetpack Compose для UI в будущих этапах.
- Room для локального хранения в будущих этапах.
- Локальный `LocalMind` abstraction для будущей интеграции локальных LLM.
- Локальные permission/device мосты без облачной синхронизации.

В этом коммите Android-код, Gradle-файлы и внешние зависимости не добавляются.

## Current status

Статус: **Phase 0 — Repository and documentation**.

Сейчас проект содержит только базовую документационную основу. Реализация Android-приложения, launcher mode, локальная база данных и интеграция LLM ещё не начаты.

## Roadmap summary

1. Создать Android skeleton без бизнес-логики.
2. Добавить launcher mode.
3. Построить shell UI Russian-first.
4. Добавить локальную Room database.
5. Реализовать память Aelita.
6. Добавить проекты и задачи.
7. Добавить запуск приложений.
8. Реализовать Action Log.
9. Добавить Background Life.
10. Создать Permission Center.
11. Подготовить Device Bridge placeholders.
12. Подготовить Local Mind abstraction.
13. Спроектировать Evolution Engine.
14. Провести polish и расширить документацию.

Подробный план находится в [`docs/ROADMAP.md`](docs/ROADMAP.md).

## Build status

Build status: **not configured yet**.

Android-проект, CI и Gradle-конфигурация будут добавлены в следующих фазах.

## External AI API warning

Aelita-Fon не должен отправлять пользовательские данные во внешние AI API. Любая будущая AI-интеграция должна быть локальной, явно документированной и отключаемой пользователем.
