# AelitaOS / Aelita-Fon

**AelitaOS** is the real target of this repository: a future custom Android-based ROM and system-level AI phone environment for the Xiaomi 11 Lite 5G NE (`lisa`). In that target, Aelita is intended to become the local system governor, policy layer, memory, planner, app governor and user-facing intelligence layer of the phone.

**Aelita-Fon** is the current Android Shell prototype. It is not the whole product. It is the future Aelita Shell / system launcher frontend that should later run as a system app or privileged component inside AelitaOS.

Aelita is **not** a chatbot app. Aelita is intended to become the primary local system interface for the user's own phone: visible, auditable, local-first and controlled by the user.

> ⚠️ Current status: **Phase 5 — ROM-first architecture pivot**.
>
> This repository does **not** yet build a flashable ROM. The current Android app does **not** yet have system privileges, cannot truly control all applications, and cannot replace Android framework behavior. True control requires ROM/system integration in later phases.

## Current completed pieces

The project has already completed the early prototype foundation:

- documentation foundation;
- minimal Android Kotlin + Jetpack Compose skeleton;
- HOME launcher mode prototype;
- Aelita Shell UI prototype.

The current app can represent the future Shell direction, but it remains a normal Android application prototype unless installed or integrated through future system/ROM work.

## New target

The project is now ROM-first.

- Target device: **Xiaomi 11 Lite 5G NE**.
- Device codename: **lisa**.
- Platform class: **Qualcomm Snapdragon 778G**.
- Final target: **AelitaOS custom Android-based ROM for lisa**.

AelitaOS should eventually integrate Aelita as:

- system launcher;
- privileged system app / `priv-app`;
- local system agent;
- policy engine;
- app governor;
- permission visibility layer;
- local memory;
- future local AI runtime;
- future framework/SystemUI integration layer.

## Iron Man style target

Aelita should become the phone's local AI command center.

The long-term target is a phone where Aelita can see device state, reason locally, propose safe actions, and control apps through explicit Android system APIs and future audited ROM hooks. It should feel like a local command center for the device, not a cloud chatbot pasted on top of Android.

Aelita must be local-first and must not depend on Google services, external AI services, hidden telemetry or cloud backends.

## What this project is not

- Not a Google Assistant clone.
- Not a normal launcher clone.
- Not a cloud assistant.
- Not a client for external LLM APIs.
- Not hidden surveillance.
- Not malware-style app control.
- Not a root exploit project.
- Not currently a flashable ROM.

Aelita's future power must come from being built into the user's own ROM with explicit permissions, audited hooks and rollback paths — not from bypassing the user or the platform secretly.

## Current limitations

The current repository contains the Aelita Shell component and architecture documents.

It does not yet include:

- a ROM source tree;
- device tree integration;
- vendor blob extraction workflow;
- kernel build workflow;
- product makefiles;
- privileged permission declarations;
- ROM signing configuration;
- flashable build artifacts;
- root commands;
- exploit code;
- cloud services;
- external AI APIs.

The Shell prototype may be selected as a HOME launcher, but launcher mode only replaces the home screen. It does not grant system privileges, app governance, permission mediation or framework control.

## Core principles

- ROM-first architecture.
- Local-first by default.
- No mandatory GMS dependency.
- No external AI APIs.
- No cloud backend.
- No hidden telemetry.
- Explicit permissions and visible system state.
- Every autonomous or semi-autonomous action must be logged.
- Dangerous or destructive actions require user confirmation.
- Framework hooks must be explicit, audited and reversible.
- Rollback strategy is mandatory.

## Documentation map

- [`docs/ROM_ARCHITECTURE.md`](docs/ROM_ARCHITECTURE.md) — future AelitaOS layer architecture.
- [`docs/DEVICE_LISA_PLAN.md`](docs/DEVICE_LISA_PLAN.md) — Xiaomi 11 Lite 5G NE planning notes.
- [`docs/APP_CONTROL_MODEL.md`](docs/APP_CONTROL_MODEL.md) — what app control means at each privilege level.
- [`docs/GOOGLE_REPLACEMENT_MODEL.md`](docs/GOOGLE_REPLACEMENT_MODEL.md) — local-first replacement model for Google-centered behavior.
- [`docs/SYSTEM_AGENT_PLAN.md`](docs/SYSTEM_AGENT_PLAN.md) — future Aelita System Agent responsibilities.
- [`docs/ROM_BUILD_PIPELINE.md`](docs/ROM_BUILD_PIPELINE.md) — high-level future ROM build pipeline.
- [`docs/SECURITY_AND_ROLLBACK.md`](docs/SECURITY_AND_ROLLBACK.md) — safety and rollback model.
- [`docs/ROADMAP.md`](docs/ROADMAP.md) — ROM-first phase plan.
- [`docs/SPEC.md`](docs/SPEC.md) — technical specification.
- [`rom/README.md`](rom/README.md) — placeholder for future ROM integration material.

## Launcher mode safety note

Aelita-Fon can be selected as an Android Home launcher. This remains an early prototype. To switch back, open Android Settings -> Apps -> Default apps -> Home app, then select another launcher. If testing on a personal phone, do not remove other launchers.

## Android skeleton status

Android skeleton status: **configured as a minimal Gradle project with a Shell UI prototype**.

The app package is `com.artraccoon.aelitafon`. Gradle wrapper is intentionally not included because binary files are avoided.

Build command:

```bash
gradle assembleDebug
```

Test command:

```bash
gradle test
```

No external AI API is used.
