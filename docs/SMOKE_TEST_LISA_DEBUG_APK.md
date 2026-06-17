# Aelita-Fon Debug APK Smoke Test on lisa

This document records a manual smoke test of the Aelita-Fon debug APK artifact on the real `lisa` test device after the debug APK GitHub Actions workflow was fixed and merged.

This result validates only the normal Android debug APK launch path. It does not validate release signing, ROM inclusion, ROM buildability, or flashing.

## Device

- Device: Xiaomi 11 Lite 5G NE
- Codename: `lisa`
- Role: test phone
- Current OS: HyperOS 2.0.6.0 UKQEUXM
- Android build: Android 14 / UKQ1.240624.001
- Bootloader: unlocked

## Artifact

- Source: GitHub Actions artifact `Aelita-Fon-debug-apk`
- Build type: debug APK
- Release signed: no
- ROM candidate: no
- Flashable: no

## Observed result

- APK installed.
- Aelita app opened.
- Aelita Shell displayed.
- Status panel displayed.
- Local Core status displayed.
- Package name displayed as `com.artraccoon.aelitafon`.
- User reported general smoke test result as OK: "Вроде все ок".

## Checklist

- Install: passed
- App launch: passed
- Shell UI visible: passed
- System Agent status visible: passed
- Local Core status visible: passed
- Package visible: passed
- Memory command: user-reported OK if applicable
- Projects command: user-reported OK if applicable
- Journal command: user-reported OK if applicable
- Apps command: user-reported OK if applicable
- Permission Center command: user-reported OK if applicable
- HOME launcher selection: needs explicit confirmation
- Reboot persistence: not yet recorded

## Known limitations

- Normal Android app only.
- No system privileges.
- No ROM hooks.
- No `priv-app` integration.
- No background life.
- No release signing.
- No ROM image.
- No flashing performed.
