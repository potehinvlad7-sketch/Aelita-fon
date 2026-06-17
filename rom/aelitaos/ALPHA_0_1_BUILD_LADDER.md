# AelitaOS Alpha 0.1 Build Ladder

## Step 0 — Normal app smoke on stock HyperOS

Aelita-Fon runs as a normal app on stock HyperOS.

Status: done.

## Step 1a — Understand Android ROM file map

Understand the main Android ROM folders, image types, dynamic partitions, SELinux policy, overlays, manifests and future lunch target naming before selecting a base.

Status: done.

## Step 1b — Select clean base candidate

Select the verified clean base ROM/source direction for Xiaomi 11 Lite 5G NE (`lisa`).

Status: not selected yet.

## Step 1c — Research real lisa base candidates

Research real ROM and source candidates that explicitly support Xiaomi 11 Lite 5G NE (`lisa`).

Status: this PR.

## Step 2 — Verify stock restore package and rollback path

Verify the stock restore package, compatibility, checksum, tools, and rollback procedure before any flash testing.

Status: next, mandatory before any flash.

## Step 3 — Prepare build host

Prepare the build host and document the exact environment.

Status: pending.

## Step 4 — Fetch/review lisa device tree, vendor, kernel strategy

Review `lisa` device tree, vendor source/blob strategy, and kernel strategy before use.

Status: pending.

## Step 5 — Build base ROM without Aelita changes

Attempt a base ROM build before adding Aelita modifications.

Status: pending.

## Step 6 — Smoke flash base ROM only after rollback is ready

Review and smoke flash the base ROM only after rollback is verified.

Status: pending.

## Step 7 — Include Aelita-Fon as preloaded app/system app

Integrate Aelita-Fon first as a preloaded or system app, not as an unreviewed privileged component.

Status: pending.

## Step 8 — Build AelitaOS Alpha 0.1 candidate

Build an AelitaOS Alpha 0.1 candidate after base ROM and app inclusion are reviewed.

Status: pending.

## Step 9 — Controlled install test

Run a controlled install test using the verified rollback path and test scope.

Status: pending.
