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


## Level 1 status — partially implemented

Normal Android app APIs are partially implemented: launchable app list, local search, and user-requested launch. This is not app governance. Deeper control still requires ROM/framework integration and is outside this MVP.

## Permission Center visibility

Permission Center is the user-visible map of Aelita control levels. It explains which app-control surfaces are active, partial, planned, locked or forbidden.

Level 0/1 behavior is partially active today through the Shell and normal Android app APIs: local UI, local state, launchable app listing and explicit user-requested launch intents. Level 2 and higher abilities remain planned or locked unless a future task adds a reviewed user-granted or privileged integration. Level 4 and higher require ROM/framework integration and are not claimed by the current Shell prototype.
