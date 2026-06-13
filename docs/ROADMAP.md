# AelitaOS / Aelita-Fon ROM-first Roadmap

This roadmap pivots the project from an Android launcher prototype into the architecture path for AelitaOS: a future custom Android-based ROM for Xiaomi 11 Lite 5G NE (`lisa`).

## Phase 0 — Repository and documentation foundation — done

- Initial README, specification, roadmap and Codex rules.
- Local-first and no external AI API boundaries established.

## Phase 1 — Android Shell skeleton — done

- Minimal Android Kotlin project.
- Jetpack Compose app skeleton.
- No business logic or privileged behavior.

## Phase 2 — Launcher mode prototype — done

- HOME launcher intent setup.
- Aelita-Fon can be selected as home launcher.
- Launcher mode limitations documented.

## Phase 3 — Shell UI prototype — done

- Russian-first Aelita Shell screen.
- Command surface and system node placeholders.
- No real app governance or system privileges.

## Phase 4 — ROM-first architecture pivot — done

- Reframe AelitaOS as the actual product.
- Reframe Aelita-Fon as Shell / launcher component.
- Add ROM, device, security, app-control and build-pipeline planning docs.
- Add `rom/` placeholders without flashable or executable content.

## Phase 5 — System Agent normal-app prototype — done

- Non-privileged System Agent abstraction.
- Local state/status reporting inside normal Android limits.
- PolicyEngine, CapabilityRegistry, DeviceStateReader and in-memory Action Log.
- Shell UI connected to deterministic local agent commands.
- No hidden services, privileged behavior, cloud or external AI API.

## Phase 6 — Local Core MVP inside Shell — done

- Persistent local memory and projects.
- Persistent action log.
- Deterministic Russian-first parser.
- Local suggestions in Shell.
- Rule-based local behavior only.
- No cloud, network or external AI API.

## Phase 7 — App list and launch MVP — done

- Local app list where allowed by Android APIs.
- Safe app launch through intents.
- Visible action logging.

## Phase 8 — Permission Center UI — this PR

- Show capabilities, permission state and risk explanations.
- No privileged permissions without explicit task.

## Phase 9 — Action Log and local memory hardening

- Durable local action log.
- Local memory model.
- User-visible edit/delete/export direction.

## Phase 10 — Lisa device research pack

- Collect device-specific research for Xiaomi 11 Lite 5G NE / lisa.
- Keep this as research documentation only.

## Phase 11 — Background Life placeholder

- Design visible background-life boundaries without adding services yet.

## Phase 12 — Notification Listener proposal layer

- Propose user-granted notification observation without implementing hidden access.

## Phase 13 — Privileged system app design

- Design system app and priv-app boundaries.
- Document permission requirements and risks.
- No implementation until reviewed.

## Phase 14 — ROM tree selection and build environment

- Choose base ROM direction.
- Verify `lisa` device tree, vendor blobs, kernel and firmware requirements.
- Document anti-rollback and restore path.

## Phase 13 — AelitaOS build environment

- Host Linux build environment plan.
- Source tree and manifest structure.
- Signing and artifact policy.

## Phase 14 — Include Aelita Shell as system app / priv-app

- Integrate Shell into ROM build as system component.
- Keep privileges minimal and documented.

## Phase 15 — Google replacement defaults

- Launcher default.
- Assistant role direction.
- Local search entry and app suggestions.
- No mandatory GMS dependency.

## Phase 16 — Notification and usage observation layer

- User-visible notification and usage observation.
- Explicit permissions.
- Action log integration.

## Phase 16 — Local Mind integration

- Local model or local reasoning integration.
- No external AI APIs.
- User-visible data boundaries.

## Phase 17 — Framework/SystemUI hooks

- Explicit, audited and reversible ROM hooks.
- SystemUI and framework integration only after rollback plan exists.

## Phase 18 — First experimental AelitaOS build for lisa

- Experimental build artifact for verified `lisa` test device.
- Not for daily-driver use until validated.

## Phase 19 — Flash test and rollback validation

- Controlled test flash.
- Restore path validation.
- Logs and failure documentation.

## Phase 20 — Daily-driver hardening

- Stability, battery, privacy and rollback hardening.
- Long-term maintenance process.

## Phase status update

- Phase 5 — System Agent normal-app prototype — done.
- Phase 6 — Local Core MVP inside Shell — done.
- Phase 7 — App list and launch MVP — done.
- Phase 8 — Permission Center UI — this PR.
- Phase 9 — Action Log and local memory hardening.
- Phase 10 — Lisa device research pack.
- Phase 11 — Background Life placeholder.
- Phase 12 — Notification Listener proposal layer.
- Phase 13 — Privileged system app design.
- Phase 14 — ROM tree selection and build environment.


## Phase updates

- Phase 6 — Local Core MVP inside Shell — done.
- Phase 7 — App list and launch MVP — done.
- Phase 8 — Permission Center UI — this PR.
- Phase 9 — Action Log and local memory hardening.
- Phase 10 — Lisa device research pack.
- Phase 11 — Background Life placeholder.
- Phase 12 — Notification Listener proposal layer.
- Phase 13 — Privileged system app design.
- Phase 14 — ROM tree selection and build environment.
