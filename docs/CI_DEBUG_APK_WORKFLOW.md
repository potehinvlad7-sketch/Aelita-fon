# Debug APK CI Workflow

## Purpose

The `Build Aelita-Fon Debug APK` GitHub Actions workflow builds a normal Android debug APK for Aelita-Fon so maintainers can perform short smoke tests on the Xiaomi 11 Lite 5G NE (`lisa`). It is a CI repair and diagnostics workflow only.

## Scope

The workflow builds **debug APK only**. The artifact is for smoke testing only and is not a release APK, ROM component, ROM release candidate, or proof that a ROM is buildable.

The workflow must not commit generated APK files, Gradle wrapper files, binaries, keystores, signing keys, ZIP/IMG files, or flashing material. APK delivery is allowed only through the GitHub Actions artifact mechanism.

## What the workflow does

On `workflow_dispatch`, pull requests to `main`, and pushes to `main`, the workflow:

1. checks out the repository;
2. sets up JDK 17;
3. sets up the Android SDK;
4. sets up Gradle from GitHub Actions instead of using a committed Gradle wrapper;
5. prints repository diagnostics before building;
6. explicitly installs the Android SDK components needed by the app, including `platforms;android-36` for `compileSdk = 36` and `build-tools;36.0.0`;
7. runs Gradle project and dependency diagnostics with `--stacktrace`;
8. builds the debug APK with `gradle :app:assembleDebug --stacktrace --info`;
9. lists files under `app/build/outputs` before upload;
10. uploads matching files from `app/build/outputs/apk/debug/*.apk` as the `Aelita-Fon-debug-apk` artifact for 7 days.

## Common failure diagnostics

### SDK platform missing

Symptoms usually mention that Android platform 36 cannot be found or that the SDK location is missing the requested package. Check the `Install Android SDK components` step and confirm it installed `platforms;android-36` successfully.

### `compileSdk` not installed

If Gradle reports that `compileSdk` requires an SDK platform that is not installed, verify the workflow still installs the same SDK platform version used by the app. The current app uses SDK 36, so CI must install `platforms;android-36` explicitly.

### Android Gradle Plugin or Kotlin dependency resolution

Failures during configuration or dependency resolution may indicate a temporary repository outage, an incompatible plugin version, or a dependency declaration issue. Start with the `Gradle dependency diagnostics` step and inspect the `gradle projects --stacktrace` and `gradle :app:dependencies --configuration debugRuntimeClasspath --stacktrace` output.

### Gradle version mismatch

This repository intentionally does not commit Gradle wrapper files. The workflow therefore uses `gradle/actions/setup-gradle` to provide Gradle. If the Android Gradle Plugin requires a different Gradle range, update the workflow Gradle version and document the reason in the PR.

### No Gradle wrapper by policy

Do not fix CI by committing `gradlew`, `gradlew.bat`, or `gradle/wrapper/*`. The project policy avoids committing wrapper binaries. Keep Gradle provisioning inside GitHub Actions unless a separate explicit policy change is approved.

## How to read failed logs

1. Open the failed GitHub Actions run.
2. Expand `Repository diagnostics` first to confirm Java, Gradle, Android SDK root, and expected repository files.
3. Expand `Install Android SDK components` to confirm licenses were accepted and SDK 36 packages installed.
4. Expand `Gradle dependency diagnostics` to find configuration or dependency resolution errors before the build task runs.
5. Expand `Build debug APK` and search for the first `FAILURE:` block and the first `Caused by:` line from `--stacktrace` output.
6. Check `Discover APK outputs` to see whether Gradle produced any files before upload.

Do not move from a failed debug APK CI run to ROM, flashing, root, or device-modification steps. Diagnose the logs and fix the app or workflow first.

## Successful artifact location

After a successful run, open the GitHub Actions run summary and download the artifact named `Aelita-Fon-debug-apk`. The artifact is retained for 7 days and should be used only for local smoke testing.
