# AelitaOS Flash Path

This is the high-level path from the current Aelita-Fon app prototype toward a cautious AelitaOS Alpha 0.1 flash candidate for Xiaomi 11 Lite 5G NE (`lisa`).

No flash should happen until all gates are complete, rollback is verified, and the selected base is confirmed for `lisa`.

## Phase A: current app smoke test on HyperOS

- Install and test Aelita-Fon on the current HyperOS system.
- Verify launcher selection, Shell, Local Core, app list/search/launch, action log, projects, memory, and Permission Center.

## Phase B: build release APK

- Produce a release APK artifact.
- Use release signing for candidates.
- Do not commit keystores.
- Record checksums.

## Phase C: select known lisa ROM base

- Review known working `lisa` ROM releases or source trees.
- Verify codename, maintainer trust, bugs, firmware requirements, and rollback notes.
- Do not use files meant for another codename.

## Phase D: install base ROM without Aelita modifications only if rollback is ready

- Validate the base ROM independently before adding Aelita modifications.
- Proceed only after the stock restore package and rollback process are verified.

## Phase E: integrate Aelita-Fon as preloaded/system app

- Start with preloaded app or non-privileged system app integration.
- Do not add privileged permissions for Alpha 0.1.

## Phase F: Alpha 0.1 flash candidate review

- Review device readiness, rollback readiness, ROM readiness, and Aelita readiness.
- Confirm checksums and exact artifact provenance.

## Phase G: controlled flash test

- Controlled testing belongs in a later explicitly approved step.
- First boot testing must record hardware, launcher, and rollback access results.
