# Windows Host Preparation Checklist

This checklist prepares a Windows PC for research and artifact handling only. It does not include flashing commands.

- [ ] Install Android platform-tools from the official Android developer source.
- [ ] Install Xiaomi or Google USB drivers if Windows does not recognize the phone correctly.
- [ ] Verify Device Manager sees the phone correctly in normal Android mode and bootloader mode.
- [ ] Verify adb/fastboot availability conceptually before relying on the PC for recovery.
- [ ] Prepare a folder layout for:
  - stock restore package;
  - custom ROM candidate;
  - Aelita APK;
  - logs and checksums.
- [ ] Install WSL2 Ubuntu only for build/research if a source build is planned.
- [ ] Reserve large disk space if ROM source work is planned.
- [ ] Prefer native Linux or a powerful WSL2 setup for a source build attempt.

Full Android ROM source builds usually need a Linux-like environment, reliable internet, large disk space, and significant RAM. The current repository alone cannot build a full ROM.
