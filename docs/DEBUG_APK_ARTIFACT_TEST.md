# Debug APK Artifact Smoke Test

This document describes the GitHub Actions debug APK artifact used for local Aelita-Fon smoke testing.

## Purpose

The `Aelita-Fon-debug-apk` GitHub Actions artifact is for short-lived manual smoke testing only. It exists so the current Android app can be installed and checked on the user's Xiaomi 11 Lite 5G NE (`lisa`) running HyperOS before any later ROM integration work.

This artifact is not a ROM candidate, not release signed, and not a flashable artifact.

## Hard boundaries

- Do not commit APK files to this repository.
- Do not commit the downloaded debug APK after a workflow run.
- Do not include the debug APK in the Alpha 0.1 ROM image.
- Do not treat the debug APK as a release artifact.
- Do not use the debug APK as a ROM release candidate.
- Release APK signing is separate future work.

## How the artifact is produced

The GitHub Actions workflow builds the normal debug variant and uploads files from:

```text
app/build/outputs/apk/debug/
```

The artifact name is:

```text
Aelita-Fon-debug-apk
```

The artifact retention period is 7 days.

The repository intentionally does not include a Gradle wrapper because binary files are avoided. The workflow uses a configured Gradle version from GitHub Actions instead of committing wrapper files.

## Manual smoke test scope

Use the downloaded debug APK only to test the following normal-app behavior:

- install;
- open app;
- set as HOME launcher;
- Shell UI;
- `статус` / status command;
- memory;
- projects;
- journal;
- app list;
- app launch;
- Permission Center.

## Result record

Record smoke test results outside the APK file itself:

- artifact name;
- APK filename;
- SHA-256 checksum;
- source Git commit;
- device model and codename;
- HyperOS / Android build number;
- test date;
- tester;
- pass/fail notes.
