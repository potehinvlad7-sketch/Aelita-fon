# AelitaOS ROM Architecture

AelitaOS is the future custom Android-based ROM target for Aelita-Fon. The current Shell app is only one component of this system.

## Key correction

Aelita Shell alone is not enough. Launcher mode only replaces the Android home screen. It does not grant full app control, privileged permissions, SystemUI control, framework mediation or permission governance.

True app governance requires system privileges and/or Android framework integration. Any framework hooks must be explicit, audited, documented and reversible.

## Layer model

### 1. Boot / firmware layer

This layer contains device boot chain, firmware, bootloader state, recovery path, modem/vendor firmware and device-specific safety constraints. AelitaOS must not assume this layer is safe to modify until the exact device state is verified.

### 2. Android base ROM layer

This is the AOSP, LineageOS or other Android-derived base that provides Android framework services, package management, permissions, settings, SystemUI, runtime and hardware abstraction layers.

### 3. Aelita privileged system layer

This future layer installs Aelita components as system app or privileged app where appropriate. It may allow controlled access to platform-level capabilities, but only through declared, reviewed and reversible integration.

### 4. Aelita Shell / Launcher layer

This is the current Aelita-Fon direction: the user-facing home screen and command surface. It presents status, commands, memory, suggestions and system modules. As a normal launcher, it cannot truly govern apps by itself.

### 5. Aelita System Agent

The future system coordinator that observes local device state, routes user commands, communicates with Shell UI, writes action logs and coordinates policy decisions. It must be modular and auditable.

### 6. Aelita App Governor

The future policy layer for app visibility, launch mediation, app suggestions, notification routing and safe app-related decisions. It requires privileged APIs or ROM hooks for deeper behavior.

### 7. Aelita Permission Center

A user-visible center for capabilities, permissions, risk explanations and access status. It must show what Aelita can do, why it can do it, and where that capability was used.

### 8. Aelita Local Mind

The local reasoning layer. Early versions may be rule-based. Later versions may use local on-device models. It must not depend on external AI APIs.

### 9. Aelita Memory

Local user memory, preferences, device context, decisions, projects and history. Memory must be editable, exportable where possible and deletable by the user.

### 10. Aelita Evolution / Proposal Engine

A proposal layer that suggests improvements, automations or configuration changes. It must not silently change code, permissions, settings or policies.

## Safety requirements

- Destructive or risky actions must require explicit user confirmation.
- All actions must be logged.
- Framework hooks must be explicit, audited and reversible.
- Rollback strategy is mandatory before ROM-level changes.
- No hidden surveillance, exfiltration or malware-like control.
- ROM integration must remain small, reviewable and staged.
