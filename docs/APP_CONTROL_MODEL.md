# Aelita App Control Model

"Aelita controls apps" must be defined by capability level. A normal Android launcher cannot secretly or completely control applications.

## Level 0 — Shell control

- Home screen.
- App shortcuts.
- Command input.
- No true app control.

## Level 1 — Normal Android app APIs

- Open apps via intents.
- Query installed packages if permission is available.
- Show local app list.
- No hidden control.

## Level 2 — User-granted Android access

- Notification listener.
- Accessibility service.
- Usage stats.
- Device admin or device owner where applicable.
- Requires explicit user enabling.

## Level 3 — Privileged system app / priv-app

- Deeper package visibility.
- Permission controller integration.
- Policy enforcement options.
- System settings access where allowed by platform signature or privileged permissions.

## Level 4 — ROM framework integration

- App launch mediation.
- Permission flow changes.
- SystemUI hooks.
- Recents/task control.
- Default assistant integration.
- Notification routing.

## Level 5 — Root/kernel/low-level

- Only for controlled development.
- Risky.
- Not first-line architecture.
- Never hidden from the user.

## Non-negotiable boundaries

- Aelita must not secretly spy on the user.
- Aelita must not silently exfiltrate data.
- Aelita must not perform destructive actions without confirmation.
- Aelita's power comes from being built into the user's own ROM, not from malware behavior.
