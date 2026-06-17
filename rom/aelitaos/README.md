# AelitaOS ROM Workspace

This directory is the future AelitaOS ROM workspace for the Xiaomi 11 Lite 5G NE (`lisa`). It starts the repository layout for real firmware work while keeping the current state honest and non-flashable.

## Current status

- This workspace is not buildable yet.
- This workspace does not contain a full Android source tree.
- This workspace does not contain proprietary vendor blobs.
- This workspace does not contain kernel, recovery, firmware, image, ZIP, APK, or other binary ROM artifacts.
- This workspace does not contain a flashable image.
- Aelita-Fon is currently the working app/system component prototype that will later be included in the ROM.

## Gated ROM phases

ROM work must proceed through gated phases:

1. base ROM/source selection;
2. stock restore verification;
3. build environment setup;
4. device tree/vendor/kernel review;
5. Aelita-Fon inclusion;
6. first build candidate;
7. flash review;
8. controlled install test.

No phase should claim buildability or flash readiness before the required inputs, artifacts, review, and rollback checks exist.
