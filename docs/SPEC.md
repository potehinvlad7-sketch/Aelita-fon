# Aelita-Fon Technical Specification

## 1. Цель

Aelita-Fon — локальная AI-native оболочка для Android, где Aelita становится основным пользовательским интерфейсом телефона. Система должна объединять память, планирование, наблюдение, управление действиями и будущую локальную LLM-интеграцию без облачного backend и без внешних AI API.

Цель MVP — создать безопасную, понятную и расширяемую основу, которая работает офлайн, хранит данные локально и готовит архитектуру для будущих уровней доступа к устройству.

## 2. Принципы

- **Offline-first:** основной сценарий работы не требует сети.
- **Local-only:** данные пользователя, память, журнал действий и контекст остаются на устройстве.
- **No external AI APIs:** запрещены внешние AI API, скрытые прокси и облачные LLM.
- **No cloud backend:** серверная часть не создаётся.
- **No cloud sync:** синхронизация через облако не добавляется.
- **Explicit permissions:** доступы к возможностям Android запрашиваются явно и объясняются пользователю.
- **Logged autonomy:** каждое автономное или полуавтономное действие записывается в Action Log.
- **User control:** Aelita может предлагать изменения, но не может молча модифицировать себя, настройки или данные.
- **Russian-first UX:** основной интерфейс и тексты ориентированы на русский язык.
- **Incremental privilege:** Accessibility, Shizuku, root, system app и Custom ROM рассматриваются только как будущие отдельные слои.

## 3. Архитектура

Высокоуровневая модель:

```text
Android OS
└── Aelita-Fon Shell
    ├── Shell UI
    ├── Command Pipeline
    ├── Context System
    ├── Local Memory
    ├── Projects & Tasks
    ├── Permission Center
    ├── Device Bridge
    ├── Local Mind
    ├── Evolution Engine
    └── Action Log
```

Aelita-Fon не заменяет Android на системном уровне в MVP. Он строит слой интерфейса, который постепенно получает контролируемый доступ к данным и действиям устройства через стандартные Android-механизмы.

## 4. Модули

### Shell UI

Главный интерфейс Aelita. Должен показывать:

- текущий статус Aelita;
- поле команды или диалога;
- рекомендации;
- карточки проектов, задач и памяти;
- журнал последних действий;
- состояние разрешений.

### Command Pipeline

Путь обработки команды пользователя:

```text
User command -> CommandParser -> IntentClassifier -> ContextBuilder -> Planner -> ActionDispatcher -> ActionLog
```

Задача pipeline — отделить ввод, классификацию, сбор контекста, планирование и исполнение. Это позволит позже подключить локальную LLM без переписывания всей оболочки.

### Local Memory

Локальная память Aelita. Хранит факты, заметки, предпочтения, связи, историю проектов и пользовательские решения. Память должна быть редактируемой, удаляемой и экспортируемой локально.

### Projects & Tasks

Модуль для целей, проектов, задач и планов пользователя. Aelita должна уметь связывать команды, заметки и действия с конкретными проектами.

### Permission Center

Единая панель доступов. Объясняет, какие возможности доступны, зачем они нужны и что произойдёт при включении.

### Device Bridge

Абстрактный слой для будущего взаимодействия с устройством: приложения, уведомления, файлы, usage stats, accessibility, Shizuku, root, system app и Custom ROM.

### Local Mind

Интерфейс для будущей локальной модели. На ранних фазах может работать как rule-based или mock implementation. Не должен обращаться к внешним AI API.

### Evolution Engine

Модуль предложений по улучшению поведения Aelita. Он может формировать рекомендации, миграции и идеи обновлений, но не имеет права молча изменять код, настройки или собственные правила.

### Action Log

Обязательный журнал действий. Фиксирует автономные действия, предложения, отказы, изменения состояния и операции с устройством.

## 5. Data model

Минимальные сущности MVP:

- `MemoryItem`
  - `id`
  - `type`
  - `title`
  - `content`
  - `tags`
  - `createdAt`
  - `updatedAt`
- `Project`
  - `id`
  - `name`
  - `description`
  - `status`
  - `createdAt`
  - `updatedAt`
- `TaskItem`
  - `id`
  - `projectId`
  - `title`
  - `description`
  - `status`
  - `priority`
  - `dueAt`
- `ActionLogEntry`
  - `id`
  - `timestamp`
  - `actor`
  - `actionType`
  - `input`
  - `plan`
  - `result`
  - `permissionState`
- `PermissionState`
  - `id`
  - `capability`
  - `status`
  - `lastChangedAt`
  - `explanation`
- `DeviceCapability`
  - `id`
  - `name`
  - `accessLayer`
  - `enabled`
  - `riskLevel`

Все данные MVP должны храниться локально.

## 6. MVP features

MVP должен включать:

- базовый shell UI;
- ввод команд пользователя;
- локальный список памяти;
- локальные проекты и задачи;
- запуск выбранных приложений через безопасные Android intents;
- Action Log;
- экран Permission Center;
- заглушки Device Bridge;
- заглушку Local Mind;
- документацию ограничений и будущих privileged layers.

## 7. Background Life

Background Life — будущий режим фонового присутствия Aelita. Он должен быть осторожным, видимым и отключаемым.

Требования:

- не выполнять скрытых действий;
- показывать активность через системные механизмы Android;
- логировать каждое действие;
- уважать энергосбережение и ограничения Android;
- не обходить ограничения системы без отдельной задачи и явного разрешения пользователя.

## 8. Permission Center

Permission Center должен объяснять доступы человеческим языком. Для каждого доступа нужно показывать:

- название возможности;
- зачем она нужна;
- что Aelita сможет делать;
- какие риски есть;
- текущий статус;
- способ включения и отключения;
- ссылку на записи Action Log, где доступ использовался.

## 9. Device Bridge

Device Bridge — слой адаптеров к Android-возможностям. Планируемые уровни:

- стандартные Android intents;
- Accessibility;
- Notification Listener;
- Usage Access;
- Storage Access Framework;
- Shizuku;
- Root;
- System app;
- Custom ROM.

В MVP этот слой должен быть безопасным и ограниченным. Root-команды, Shizuku-интеграция и системные привилегии запрещены без отдельной явной задачи.

## 10. Local Mind abstraction

`LocalMind` — интерфейс для рассуждений, классификации намерений, генерации планов и объяснений. Он должен поддерживать несколько реализаций:

- `RuleBasedMind` для раннего MVP;
- `MockMind` для тестов и UI-прототипов;
- будущий адаптер локальной LLM;
- будущий адаптер on-device embeddings.

Запрещено добавлять внешние AI API или сетевую LLM-интеграцию.

## 11. Evolution Engine

Evolution Engine анализирует локальные паттерны использования и предлагает улучшения:

- новые правила;
- улучшения UX;
- варианты автоматизации;
- миграции данных;
- изменения поведения Aelita.

Ограничения:

- предложения должны быть видимыми;
- пользователь должен подтверждать изменения;
- изменения должны логироваться;
- Aelita не должна молча менять собственные правила или код.

## 12. Logging requirements

Action Log обязателен для:

- автономных действий;
- полуавтономных действий;
- запуска приложений;
- изменений памяти;
- изменений проектов и задач;
- запросов разрешений;
- использования Device Bridge;
- предложений Evolution Engine;
- ошибок и отказов.

Запись должна быть понятной пользователю и пригодной для отладки.

## 13. UX requirements

- Интерфейс Russian-first.
- Действия Aelita объясняются простым языком.
- Опасные действия требуют подтверждения.
- Пользователь должен видеть, что Aelita знает, помнит и предлагает.
- Память должна быть редактируемой.
- Журнал действий должен быть доступен из главного интерфейса.
- Состояние offline/local-only должно быть явно обозначено.

## 14. MVP readiness checklist

- [ ] Android skeleton создан отдельной задачей.
- [ ] Нет внешних AI API.
- [ ] Нет cloud backend.
- [ ] Нет cloud sync.
- [ ] UI Russian-first.
- [ ] Есть базовый Shell UI.
- [ ] Есть локальная база данных.
- [ ] Есть Memory module.
- [ ] Есть Projects module.
- [ ] Есть Action Log.
- [ ] Есть Permission Center.
- [ ] Есть безопасный App Launcher.
- [ ] Есть Device Bridge placeholders.
- [ ] Есть Local Mind abstraction.
- [ ] Все автономные действия логируются.
- [ ] Evolution Engine не изменяет систему без подтверждения.

## ROM-first product definition

AelitaOS is the actual product. Aelita-Fon is the current Android app prototype and future Shell component of that product.

The end goal is a phone where Aelita is the primary interface and local intelligence layer. Aelita should become the user's local system governor, policy layer, planner, memory, permission visibility layer and future local AI controller inside a custom Android-based ROM.

This project is not a Google Assistant clone. It is not a normal launcher clone. It is a custom ROM project with an AI-native system shell.

The current app should therefore be treated as the Aelita Shell / system launcher frontend, not as the whole product. True application governance, permission mediation, SystemUI behavior and framework-level control require future system app, privileged app or ROM/framework integration.

## 12. System Agent normal-app prototype

Текущий System Agent — безопасный normal-app прототип будущего системного слоя AelitaOS. Он моделирует будущие обязанности системного агента, но не использует системные привилегии и не выполняет реальное управление устройством.

Прототип включает:

- `AelitaSystemAgent` как интерфейс между Shell UI и локальной системной логикой;
- `PolicyEngine`, который оценивает действие до ответа;
- `CapabilityRegistry`, который честно показывает уровни доступа от Shell до будущей ROM-интеграции;
- `DeviceStateReader`, ограниченный безопасной информацией normal-app уровня;
- persistent `ActionLogStore` на `SharedPreferences` для Local Core MVP; Room и фоновая синхронизация не используются.

Все текущие действия являются локальными deterministic no-op операциями или статусными отчётами. Прототип не запрашивает опасные Android permissions, не читает список установленных приложений, не запускает приложения, не использует Accessibility, Notification Listener, root, сеть, cloud backend или внешний AI API.

## Local Core MVP

Aelita Shell now contains a normal-app Local Core MVP. The user path is deterministic and local-only:

`command -> parser -> System Agent policy/orchestration -> Local Core -> persistent storage -> action log -> response`.

The Local Core supports Russian-first commands for memory, projects, journal, suggestions, status/help, app-control placeholders, and ROM status placeholders. It does not implement real AI, cloud calls, installed-app access, app launching, root operations, Accessibility control, Notification Listener control, or ROM hooks.

### Storage

The MVP uses Android `SharedPreferences` with built-in `org.json` JSON arrays for memory entries, project entries, and the action log. JSON parsing is defensive: malformed storage should not crash the app and should fall back to empty lists with a warning log. This is intentionally temporary; a later hardening phase can migrate local state to Room after the data model stabilizes.

All Local Core state is local-only. There is no network dependency, no cloud backend, and no external AI API.
