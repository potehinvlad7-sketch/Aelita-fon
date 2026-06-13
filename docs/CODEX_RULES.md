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

## Strict ROM rules

- Codex must never add flashing commands without an explicit task.
- Codex must never add root or exploit code.
- Codex must never add telemetry or cloud APIs.
- Codex must never pretend a ROM is buildable before it is.
- Codex must clearly mark placeholders.
- Codex must keep every PR small, auditable and reversible.
- Codex must not modify dangerous files or privileged permissions without an explicit task.

For ROM-related PRs, Codex must list:

- changed docs;
- created folders;
- whether any executable scripts were added;
- whether any flashing instructions were added;
- whether any privileged permission declarations were added.

## Strict ROM/device rules

- Planning PRs must not include flashing commands.
- Flashing commands require an explicit user request and a complete rollback checklist.
- Never use non-`lisa` files for Xiaomi 11 Lite 5G NE work.
- Never suggest bootloader relock after an unofficial ROM.
- Never claim ROM flashability without artifact, checksum, source/base, and rollback plan.
- Separate planning, building, and flashing PRs.
- Always show whether binaries, scripts, and commands were added.
- Never commit keystores or secrets.
- Never commit APKs, ZIPs, images, vendor blobs, kernel images, recovery images, or other binary firmware artifacts unless a later explicit artifact policy allows it.
