# Aelita-Fon APK Artifact Requirements

This document defines the APK artifact rules for Aelita-Fon Alpha 0.1 preparation.

## Filename

Use this filename format for Alpha 0.1 APK candidates:

```text
Aelita-Fon-alpha0.1-YYYYMMDD.apk
```

Replace `YYYYMMDD` with the build date.

## Checksum

SHA-256 is required for every release APK candidate.

Record the checksum with the release notes or local test record before installation testing.

## Build type

Release builds are preferred for shared testing and are required for ROM candidate review.

Debug artifacts are allowed only for local smoke tests and must not be used as ROM candidates.

## Signing

Release signing is required before an APK can be considered for a ROM candidate.

Signing keys, keystores, passwords, and secret configuration files must stay outside Git.

## Storage

Do not commit APK artifacts to Git.

Store APK candidates by either:

- attaching the APK to a GitHub Release with its SHA-256 checksum; or
- storing the APK locally with its SHA-256 checksum and source commit recorded.

## Security and privacy boundaries

APK artifacts must not contain:

- API keys;
- secrets;
- telemetry;
- cloud service configuration;
- external AI API configuration.
