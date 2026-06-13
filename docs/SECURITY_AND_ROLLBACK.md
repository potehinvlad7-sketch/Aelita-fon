# Security and Rollback Model

AelitaOS must be designed as a local-first, user-visible and rollback-aware system.

## Security principles

- Local-first.
- No cloud by default.
- No external AI API.
- No hidden telemetry.
- Action log required.
- Permission transparency.
- Destructive actions require confirmation.
- No hidden surveillance.
- No malware-like behavior.

## Staged rollout

1. App prototype.
2. Launcher.
3. System app.
4. Privileged app.
5. ROM hooks.
6. Deeper framework integration.

Each stage must be separately reviewed and reversible.

## Rollback plan

Future work must document rollback at each layer:

- app rollback;
- system app disable path;
- ROM restore path;
- fastboot/recovery restore path;
- backup requirements.

## Current scope

This PR does not add privileged permissions, flashing scripts, root commands, exploit code, binaries, cloud services or external AI APIs.
