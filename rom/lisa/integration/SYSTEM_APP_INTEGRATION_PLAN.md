# System App Integration Plan

## Stage 0: manual install smoke test

Install the APK manually on HyperOS for app-level smoke testing. This does not require system privileges.

## Stage 1: preloaded app

Include Aelita-Fon as a preloaded app in the ROM image, not privileged.

## Stage 2: system app

Install Aelita-Fon as a system app while still adding no privileged permissions.

## Stage 3: priv-app candidate

Consider priv-app only after policy, permission, sepolicy, and rollback hardening are reviewed.

## Stage 4: framework/ROM hooks

Framework and ROM hooks are later work only. They require explicit design, audit, and rollback planning.

## Alpha 0.1 target

Alpha 0.1 targets Stage 1 or Stage 2 only. It must not add privileged permissions.
