# AelitaOS / Aelita-Fon ROM-first Roadmap

This roadmap pivots the project from an Android launcher prototype into the architecture path for AelitaOS: a future custom Android-based ROM for Xiaomi 11 Lite 5G NE (`lisa`).

## Completed foundation

- Phase 0 — Repository and documentation foundation — done.
- Phase 1 — Android Shell skeleton — done.
- Phase 2 — Launcher mode prototype — done.
- Phase 3 — Shell UI prototype — done.
- Phase 4 — ROM-first architecture pivot — done.
- Phase 5 — System Agent normal-app prototype — done.
- Phase 6 — Local Core MVP inside Shell — done.
- Phase 7 — App list and launch MVP — done.
- Phase 8 — Permission Center UI — done.
- Phase 9 — Alpha 0.1 Flash Candidate Pack — done.
- Phase 10 — Aelita-Fon release APK build path — done.
- Phase 11 — Build/test APK on Windows — done.
- Phase 12 — Debug APK CI hardening — done.

## Current phase

## Phase 13 — lisa debug APK smoke test recorded — this PR

- Record that the GitHub Actions debug APK artifact path works for a manual normal-app smoke test.
- Record that the `Aelita-Fon-debug-apk` artifact was downloaded, installed, and opened on the real Xiaomi 11 Lite 5G NE (`lisa`) test phone.
- Record that the Aelita Shell, status panel, Local Core status, and package name were visible.
- Keep the result scoped to a basic debug APK smoke test only.
- Do not claim release signing, ROM inclusion, ROM buildability, or flashability.

## Next phases

- Phase 14 — Lisa base ROM candidate selection.
- Phase 15 — Stock restore package verification.
- Phase 16 — Base ROM smoke flash planning.
- Phase 17 — Aelita preloaded/system app integration draft.
- Phase 18 — Alpha 0.1 build candidate review.
- Phase 19 — Controlled install test.

## Long-term phases

- Privileged system app design after non-privileged Alpha validation.
- Google replacement defaults after safe launcher/system-app behavior is verified.
- Notification and usage observation layers only with explicit user-granted access.
- Local Mind integration without external AI APIs.
- Framework/SystemUI hooks only after audited rollback paths exist.
- Daily-driver hardening only after stability, battery, privacy, and rollback are proven.
