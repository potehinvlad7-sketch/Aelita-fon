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

## Current scope

No service, permission, privileged code or background behavior is added in this PR.
