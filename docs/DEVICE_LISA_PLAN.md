# Xiaomi 11 Lite 5G NE / lisa Device Plan

AelitaOS targets the Xiaomi 11 Lite 5G NE.

- Device: Xiaomi 11 Lite 5G NE
- Codename: `lisa`
- Platform: Qualcomm Snapdragon 778G class
- Target: custom Android-based ROM with Aelita as the primary local system interface

## Required verification before any flashing

Before flashing anything, verify and document the exact current device state:

- bootloader status;
- current firmware version;
- Android/MIUI/HyperOS base;
- anti-rollback risk;
- recovery availability;
- vendor blobs availability;
- kernel source and device tree availability;
- backup status.

## Development rules

- Never flash unverified builds to the only daily-use phone.
- Keep stock recovery or fastboot restore path documented.
- Keep another working phone and computer available during flashing.
- No blind flashing.
- No locking bootloader after unofficial ROM unless the build is verified safe for that exact device.

## Scope of this document

This PR is planning only. It does not include flashing commands, restore commands, build commands, binaries, device images or executable scripts.
