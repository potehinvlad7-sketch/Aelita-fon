# Aelita-Fon APK Release Process

This document defines the safe path for producing an Aelita-Fon APK artifact for later AelitaOS Alpha 0.1 testing and possible ROM inclusion.

Aelita-Fon is still a normal Android application candidate. This repository does **not** produce a ROM, does **not** publish APK artifacts, and does **not** contain signing keys or keystores.

## Build types

### Debug APK

Debug APKs are allowed only for local smoke tests during development.

Debug APKs must not be treated as ROM candidates because they are developer-signed, not release-signed, and are not suitable for preload or system-app review.

### Release APK

A release APK is required before Aelita-Fon can be reviewed as a ROM preload candidate.

Release APK requirements:

- build from a reviewed source revision;
- sign with a local release keystore;
- keep the keystore outside Git;
- record a SHA-256 checksum for the exact APK file;
- test on the current stock HyperOS device before any ROM integration draft uses it.

## Signing and keystore rules

Generate the release keystore locally on the build machine. Do not commit it.

Never commit:

- keystores;
- signing keys;
- passwords;
- `key.properties` files containing secrets;
- generated APKs;
- generated binary artifacts.

If a release APK is created for Alpha 0.1 evaluation, store it outside the repository and keep its checksum with the release notes or local test record.

## Artifact checksum

Every release APK candidate must have a SHA-256 checksum recorded before installation testing.

The checksum record should include:

- APK filename;
- source Git commit;
- build type;
- signing mode;
- SHA-256 value;
- build date;
- tester/device used for smoke testing.

## Pre-ROM HyperOS test gate

Before ROM inclusion is considered, install and test the exact signed APK on the current stock/current HyperOS setup for Xiaomi 11 Lite 5G NE (`lisa`).

The APK must be verified for:

- launch;
- HOME launcher selection;
- Shell UI;
- command input;
- memory;
- projects;
- action log;
- app list;
- app launch;
- Permission Center.

Passing this gate only means the APK is ready for further ROM integration planning. It does not mean the ROM is buildable or flashable.
