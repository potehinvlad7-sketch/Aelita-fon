# Alpha 0.1 Flash Candidate Gate

A flash candidate may exist only after every item below is true. This document does not authorize flashing and intentionally does not include flashing commands.

## Device readiness

- [ ] Bootloader confirmed unlocked.
- [ ] Codename confirmed as `lisa` on the device.
- [ ] Current firmware recorded: HyperOS 2.0.6.0 UKQEUXM.
- [ ] Android build recorded: UKQ1.240624.001.
- [ ] Battery above safe threshold.
- [ ] Data backup complete.
- [ ] Test phone confirmed.
- [ ] Second working phone confirmed.

## Rollback readiness

- [ ] Exact stock ROM / fastboot restore package for current region/version identified.
- [ ] Checksum/hash recorded.
- [ ] Restore method understood.
- [ ] Fastboot access tested.
- [ ] USB cable and Windows drivers verified.
- [ ] No bootloader relock planned.

## ROM readiness

- [ ] Base ROM selected.
- [ ] Source/release trust checked.
- [ ] Target codename `lisa` confirmed.
- [ ] Known bugs reviewed.
- [ ] Firmware/vendor requirements reviewed.
- [ ] Installation method reviewed but not executed in this PR.

## Aelita readiness

- [x] Debug APK smoke test passed on `lisa` as a normal Android app.
- [ ] Release APK ready.
- [ ] Release signing ready.
- [ ] ROM inclusion ready.
- [ ] Release APK build path documented.
- [ ] APK signing approach documented.
- [ ] System/preload integration approach chosen.
- [ ] HOME launcher behavior known.
- [ ] No privileged permissions added.

## APK readiness status

APK readiness is only partially satisfied:

- Debug APK smoke test: passed.
- Release APK: not ready.
- Release signing: not ready.
- ROM inclusion: not ready.

Alpha 0.1 is not flashable from this repository.
