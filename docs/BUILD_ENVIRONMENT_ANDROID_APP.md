# Android App Build Environment

This document describes safe build environment expectations for the Aelita-Fon Android app APK path.

The repository does not include a Gradle wrapper because binary files are intentionally avoided. Use the project build files with a compatible local Gradle installation when no wrapper exists.

## Windows path

Android Studio is recommended on Windows for the app build path.

Recommended setup:

- install Android Studio;
- install an Android SDK matching the project compile SDK needs;
- use a JDK compatible with the project's Gradle and Android Gradle Plugin versions;
- use the project Gradle wrapper if one exists in the future;
- otherwise use a compatible local Gradle installation;
- keep release keystores outside the repository.

Avoid the JDK 25 tooling issue observed in the Codex environment. Prefer a stable JDK supported by the Android Gradle Plugin, such as JDK 17, unless the project build files are intentionally updated.

## Linux / WSL path

Linux or WSL can be used for the Android app build if Android SDK, Gradle, and a compatible JDK are installed.

This path is for app builds only. A full ROM build still needs a stronger Linux setup, significantly more storage, a complete ROM source tree, device/vendor/kernel inputs, and a separate build plan.

## Known Codex environment issue

The Codex environment previously failed during Android build setup because of Java 25.0.2 and Android Gradle Plugin resolution/tooling compatibility.

Treat this as an environment/tooling issue, not necessarily app code failure. Validate the app build on a properly configured Android Studio or compatible local Gradle environment before drawing conclusions about app readiness.
