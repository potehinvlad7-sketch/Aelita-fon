# Aelita-Fon APK Release Plan

Aelita-Fon must produce a clean release APK artifact before ROM inclusion is considered.

## Release artifact policy

- Create a release APK later from the app project.
- Debug APK is acceptable only for local smoke tests.
- Release signing is required for an Alpha candidate.
- Do not commit keystores or signing keys.
- Record checksum for every candidate APK.
- Do not add external AI API keys.
- Do not add `INTERNET` permission.
- Do not add dangerous permissions.

## Pre-ROM smoke test on current HyperOS

Before ROM inclusion, test the APK on the current HyperOS install and verify:

- App launches.
- HOME launcher selection works.
- Memory/projects/log behavior works.
- App list/search/launch works.
- Permission Center opens and reports limits honestly.
