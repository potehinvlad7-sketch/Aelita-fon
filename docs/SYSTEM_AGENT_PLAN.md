# Aelita System Agent Plan

The Aelita System Agent is the future local coordinator between the Shell UI, system state, permissions, app governance, local memory and Local Mind.

## Responsibilities

The System Agent should eventually:

- observe local device state;
- maintain action log;
- coordinate permission center;
- route user commands;
- mediate app launching;
- provide system status;
- communicate with Shell UI;
- later communicate with Local Mind;
- later manage proposal engine.

## Required properties

The System Agent must be:

- local-first;
- auditable;
- user-confirmed for dangerous actions;
- rollback-aware;
- modular.

## Future packages/modules

Potential future modules:

- `shell`
- `core`
- `memory`
- `projects`
- `apps`
- `logs`
- `permissions`
- `device`
- `background`
- `evolution`
- `localmind`
- `rombridge`
- `systemagent`
- `policy`


## Current prototype status

The first System Agent prototype is implemented as a safe normal Android app scaffold. It is connected to the Shell UI and handles deterministic local commands for status, capabilities, policy, logs, ROM context, and normal Android app list/search/launch requests.

Current boundaries:

- no privileged Android behavior;
- no privileged app control;
- no non-launchable installed-app querying;
- no dangerous permissions;
- no background service;
- no ROM bridge;
- no cloud backend;
- no external AI API;
- persistent local action log for the Local Core MVP.

The prototype exists to prepare package structure and policy boundaries for later AelitaOS ROM/system integration without pretending that those privileges are active today.

## Current scope

No service, permission, privileged code or background behavior is added in this PR.

## Current status — Local Core MVP

The System Agent remains a normal Android app orchestrator. It now delegates local memory, project, journal, suggestion, and help commands to the Local Core while keeping status, capabilities, ROM information, normal Android app list/search/launch, and policy explanations honest about normal-app limits.

No privileged control is implemented. App launching is limited to explicit user-requested Android intents. There is no Accessibility service, Notification Listener, root bridge, Shizuku bridge, background service, cloud backend, external AI API, or ROM integration.


## Current status — App List and Launch MVP

System Agent can route app list, search, and launch requests through normal Android APIs. There is no privileged behavior: no root, Accessibility, Notification Listener, shell automation, or background service. All app list/search/launch attempts are logged in the persistent local action log.
