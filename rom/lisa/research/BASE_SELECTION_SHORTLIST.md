# Base Selection Shortlist

No base ROM is selected in this PR.

## A. Preferred source-build path

**Candidate:** AOSP/Lineage-like source base using verified `lisa` device, vendor and kernel sources.

**Status:** Preferred long-term path, not selected until sources are verified.

**Why it is preferred:**

- Exact `lisa` support is visible in LineageOS wiki and device tree naming.
- AOSP/Lineage-like source is cleaner for future AelitaOS changes than adapting an opaque existing ROM package.
- Better fit for local-first and no-GMS goals.

**Required before selection:**

- Verify current LineageOS branch and build freshness.
- Verify device tree, vendor tree, kernel tree and local manifest.
- Verify licenses and proprietary blob handling.
- Verify firmware, recovery, install and rollback requirements.
- Verify known bugs on Xiaomi 11 Lite 5G NE (`lisa`).

## B. Existing ROM reference path

**Candidate:** crDroid official `lisa` Android 14 reference.

**Status:** Confirmed exists but old, not selected until freshness, bugs, source, install and rollback are checked.

**Why it remains useful:**

- Official crDroid downloads list explicitly includes Xiaomi 11 Lite 5G NE (`lisa`).
- Android 14 reference may help compare device behavior and source tree choices.

**Why it is not selected:**

- Last build is old: 2024-01-30.
- Firmware/recovery requirements and known bugs are not verified.
- Source completeness and current branch health are not verified.

## C. GSI fallback path

**Candidate:** Generic Android GSI.

**Status:** Fallback experiment only, not final AelitaOS base.

**Why it is limited:**

- It is not `lisa`-specific.
- Device-specific camera, modem, sensors, fingerprint, display and power behavior may be broken or incomplete.
- It still requires verified firmware, partition and rollback safety.

## Final recommendation

Do not flash yet. The next PR must verify stock rollback before any flash.
