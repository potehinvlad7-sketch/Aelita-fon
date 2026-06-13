# Build Host Requirements

## Full ROM build host

- Native Linux is preferred for full Android ROM builds.
- WSL2 may work for research and some builds, but it can be slower or problematic for large source trees and filesystem-heavy operations.
- Large disk space is required for source, build output, caches, and logs.
- High RAM is strongly recommended; low-memory systems may fail or become impractically slow.
- JDK and Android build tooling versions must match the selected ROM source tree.
- Git and the `repo` tool are normally required for Android source checkout workflows.
- Reliable internet is required for large source sync operations.

## Current repository reality

The current Aelita-Fon repository builds the app component, not a full ROM. It does not contain a ROM source tree, device tree, vendor blobs, kernel build workflow, recovery image, product makefiles, or flashable artifacts.
