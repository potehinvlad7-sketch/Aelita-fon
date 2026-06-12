# Codex Rules for Aelita-Fon

## Workflow

- 1 task = 1 branch = 1 PR.
- Keep PRs small.
- В каждом PR показывать modified files.
- В каждом PR показывать new files.
- В каждом PR показывать deleted files.
- Run checks before finishing when checks exist.
- Если checks нельзя запустить, явно указать причину.

## Architecture restrictions

- Do not add external AI APIs.
- Do not add cloud backend.
- Do not add cloud sync.
- Do not add analytics.
- Do not add ads.
- Do not implement hidden behavior.
- Do not implement root commands without a separate explicit task.
- Do not add Shizuku, Accessibility automation or privileged flows without a separate explicit task.
- Do not silently modify Aelita behavior, rules, memory schema or permissions.

## Product rules

- Always keep UI Russian-first.
- Always keep offline-first.
- Keep data local by default.
- Every autonomous action must be logged.
- Dangerous actions must require explicit user confirmation.
- Aelita may suggest updates, but must not silently modify herself.

## Documentation rules

- Документация должна быть ясной, практичной и структурированной.
- Технические идентификаторы можно оставлять на английском.
- Все новые capabilities должны быть описаны в Permission Center и Action Log requirements.
- Любой новый доступ к устройству должен объяснять риск для пользователя.
