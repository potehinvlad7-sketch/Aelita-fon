# Aelita-Fon Architecture

## High-level architecture tree

```text
Aelita-Fon
├── Shell UI
│   ├── Home Screen
│   ├── Command Input
│   ├── Status Cards
│   ├── Memory Views
│   ├── Project Views
│   └── Action Log Views
├── Command Pipeline
│   ├── CommandParser
│   ├── IntentClassifier
│   ├── ContextBuilder
│   ├── Planner
│   └── ActionDispatcher
├── Local Data Layer
│   ├── MemoryStore
│   ├── ProjectStore
│   ├── TaskStore
│   ├── PermissionStore
│   └── ActionLogStore
├── Permission Center
│   ├── Capability Registry
│   ├── Permission Explainers
│   └── Risk Labels
├── Device Bridge
│   ├── App Intents
│   ├── Notifications Placeholder
│   ├── Usage Access Placeholder
│   ├── Storage Access Placeholder
│   └── Privileged Layers Placeholder
├── Local Mind
│   ├── RuleBasedMind
│   ├── MockMind
│   └── Future Local LLM Adapter
└── Evolution Engine
    ├── Suggestion Builder
    ├── Change Review
    └── User Confirmation
```

## Module descriptions

### Shell UI

Главный Russian-first интерфейс Aelita. Показывает команды, состояние, память, проекты, задачи, разрешения и журнал действий. UI должен постоянно напоминать, что система offline-first и local-only.

### Command Pipeline

Слой обработки пользовательских команд. Он должен быть модульным, чтобы ранняя rule-based логика могла позже быть заменена или усилена локальной LLM.

### Local Data Layer

Локальное хранилище памяти, проектов, задач, разрешений и Action Log. В MVP данные не уходят в облако и не синхронизируются через backend.

### Permission Center

Единая точка управления возможностями. Пользователь должен понимать, какие доступы включены, зачем они нужны и какие действия с ними выполнялись.

### Device Bridge

Абстракция над возможностями Android. В ранних фазах Device Bridge должен быть ограничен безопасными действиями, например запуском приложений через стандартные intents. Привилегированные уровни добавляются только отдельными задачами.

### Local Mind

Контракт локального интеллекта Aelita. Он отвечает за классификацию, объяснения, планирование и будущую интеграцию локальных моделей. Внешние AI API запрещены.

### Evolution Engine

Механизм предложений по развитию поведения Aelita. Он не выполняет silent self-modification и требует подтверждения пользователя для изменений.

## Command flow

```text
User command
-> CommandParser
-> IntentClassifier
-> ContextBuilder
-> Planner
-> ActionDispatcher
-> ActionLog
```

Описание шагов:

1. `User command` — пользователь вводит команду на русском или через UI-действие.
2. `CommandParser` — нормализует ввод, выделяет параметры и базовую структуру.
3. `IntentClassifier` — определяет намерение: память, проект, запуск приложения, вопрос, настройка или действие.
4. `ContextBuilder` — собирает локальный контекст: память, проекты, разрешения, состояние устройства и последние действия.
5. `Planner` — строит безопасный план, выбирает нужные capabilities и определяет, требуется ли подтверждение.
6. `ActionDispatcher` — выполняет разрешённые действия через локальные модули или Device Bridge.
7. `ActionLog` — записывает команду, план, результат, ошибки и использованные разрешения.

## Future privileged access layers

Потенциальные уровни доступа:

- Accessibility.
- Notification Listener.
- Usage Access.
- Storage Access Framework.
- Shizuku.
- Root.
- System app.
- Custom ROM.

Эти уровни не входят в начальный documentation-only commit. Каждый слой должен добавляться отдельной задачей, с отдельным PR, описанием рисков, UI в Permission Center и обязательным логированием.

## Local AI integration plan

Aelita-Fon проектируется для будущей локальной AI-интеграции:

1. На ранних фазах использовать `RuleBasedMind` и `MockMind`.
2. Зафиксировать `LocalMind` contract до подключения моделей.
3. Добавить локальную классификацию намерений без сети.
4. Подготовить локальные embeddings или индекс памяти, если это потребуется.
5. Подключать только on-device LLM runtime, если он работает без внешних AI API.
6. Все AI-действия логировать через Action Log.
7. Пользователь должен видеть, какие данные попали в локальный контекст модели.

Запрещено добавлять OpenAI, Anthropic, Google Gemini, облачные прокси, telemetry-first SDK или любые другие внешние AI API.
