# Aelita-Fon APK Smoke Test on Current HyperOS

Use this checklist on the current stock/current HyperOS installation before any AelitaOS Alpha 0.1 ROM integration attempt.

This checklist is for normal APK installation validation only. It does not contain flashing commands and does not prove ROM buildability.

## Device context

- Device: Xiaomi 11 Lite 5G NE
- Codename: `lisa`
- Current OS target for pre-ROM test: HyperOS 2.0.6.0 UKQEUXM / Android 14
- APK under test: record exact filename and SHA-256 separately

## Smoke test checklist

- [ ] APK installs.
- [ ] APK opens.
- [ ] Aelita appears in launcher choices if HOME is selected.
- [ ] Aelita Shell opens as HOME.
- [ ] Command `статус` works.
- [ ] Command `память` works.
- [ ] Command `запомни тест` works.
- [ ] Command `добавь проект тест` works.
- [ ] Command `журнал` works.
- [ ] Command `приложения` works.
- [ ] Command `права` works.
- [ ] Command `открой <safe app>` works.
- [ ] App survives reboot as installed app.
- [ ] No network permission requested.
- [ ] No dangerous permission requested.

## Result record

Record the result outside the APK file itself:

- APK filename;
- SHA-256 checksum;
- source Git commit;
- device build number;
- test date;
- tester;
- pass/fail notes.

## Debug APK smoke test source

For pre-ROM smoke testing, the APK source may be the GitHub Actions artifact named `Aelita-Fon-debug-apk` from the `Build Aelita-Fon Debug APK` workflow.

Rules for this source:

- download the artifact manually from GitHub Actions;
- do not commit the downloaded APK;
- do not treat the debug APK as a release artifact;
- do not treat the debug APK as a ROM candidate;
- do not include the debug APK in the Alpha 0.1 ROM image;
- record the source commit and SHA-256 checksum before testing.
