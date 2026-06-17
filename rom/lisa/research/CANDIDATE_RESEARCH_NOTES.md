# Candidate Research Notes

## Scope

This PR researched real, verifiable ROM and source candidates for AelitaOS Alpha 0.1 on Xiaomi 11 Lite 5G NE (`lisa`). It did not flash, download ROM packages, clone source repositories, add binaries, add blobs, add images or claim buildability.

## What was searched

- Official `lisa` support status in LineageOS.
- Official crDroid downloads for Xiaomi 11 Lite 5G NE / `lisa`.
- PixelOS, Evolution X and de-Googled ROM candidates with exact `lisa` support.
- Public GitHub source tree names for `android_device_xiaomi_lisa`, `android_vendor_xiaomi_lisa`, `android_kernel_xiaomi_lisa` and related shared kernel trees.
- Generic GSI path as a non-device-specific fallback.

## What was confirmed

- LineageOS has an official wiki entry for Xiaomi 11 Lite 5G NE (`lisa`) and an official `LineageOS/android_device_xiaomi_lisa` repository.
- crDroid official downloads list includes Xiaomi 11 Lite 5G NE (`lisa`) for crDroid 9 / Android 13 and crDroid 10 / Android 14, with last build shown as 2024-01-30.
- PixelOS has an official `lisa` page listing Xiaomi 11 Lite 5G NE, active status, version 16, monthly release cadence, last updated 2026-04-10, and a named maintainer.
- Evolution X official devices list includes `lisa` as Xiaomi 11 Lite NE 5G / Mi 11 LE, and an XDA thread title confirms an official Android 14 `lisa` build dated 2024-11-16.
- Public source candidates exist for LineageOS, crDroid and ArrowOS-style `lisa` trees, but they still need branch, dependency, blob and license review.
- A generic GSI path exists, but it is not `lisa`-specific and is not a final base.

## What was rejected or downgraded

- Any candidate without exact `lisa` or Xiaomi 11 Lite 5G NE support was not added as a real supported candidate.
- iodéOS was rejected for now because this PR did not confirm an official `lisa` support page.
- /e/OS was downgraded to needs-verification because a community thread mentions `lisa`, but this PR did not verify a current official supported-device/download page.
- Telegram-only, YouTube-only and forum-only ROM mentions were not treated as sufficient for selection.
- Historical ArrowOS/Kaleidoscope-style trees are stale references, not preferred bases.

## What is still unknown

- Exact current LineageOS build freshness, supported branch, install package status, recovery requirement and firmware requirement.
- Whether PixelOS and Evolution X publish complete, current and clean `lisa` device/vendor/kernel source sets suitable for review.
- Which kernel tree LineageOS currently uses for `lisa` and whether it is shared `sm7325` or device-specific.
- Whether a maintained local manifest exists for the preferred source-build path.
- Exact known bugs for each candidate on `lisa`.
- Whether stock HyperOS 2.0.6.0 UKQEUXM rollback packages and procedures are verified for the test device.

## Cleanest-looking candidates

1. **LineageOS source-build path** — best long-term fit if device/vendor/kernel sources and local manifest are verified. It is AOSP-like, official, exact-device, and better aligned with a no-GMS AelitaOS direction than Pixel-styled ROMs.
2. **crDroid official `lisa` Android 14** — useful reference because support is exact and official, but it is stale and not selected.
3. **PixelOS official `lisa`** — fresh official support, useful as a compatibility reference, but likely less suitable as a clean no-Google base because PixelOS is Pixel/Google-experience oriented.
4. **Evolution X official `lisa`** — verified exact-device reference, but not selected until source, firmware, recovery and bugs are checked.
5. **GSI fallback** — acceptable only as a controlled experiment after rollback verification; not final AelitaOS base.

## Why unsupported or stale ROMs are risky

Unsupported or stale ROMs can carry outdated security patches, old vendor assumptions, incompatible firmware requirements, broken recoveries, unreviewed blobs, abandoned kernels or device-specific bugs. On a dynamic-partition Xiaomi device, flashing without verified stock rollback can turn a research mistake into a recovery problem. For this project, no candidate may be selected or flashed until source, recovery, firmware and rollback are verified.

## Safety recommendation

Do not flash yet. The next PR must verify the stock rollback / restore package for the exact `lisa` test device before any ROM, recovery, GSI or source-built image is tested.
