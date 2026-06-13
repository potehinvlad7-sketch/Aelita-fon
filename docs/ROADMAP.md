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

## Current phase

## Phase 10 — Aelita-Fon release APK build path — this PR

- Document the safe debug and release APK build path.
- Require local release signing without committed keystores or keys.
- Require SHA-256 checksum recording for release APK artifacts.
- Define the HyperOS APK smoke test checklist before ROM inclusion.
- Draft future ROM preload placement options without claiming ROM buildability.
- Keep the PR safe: no APKs, no binaries, no keystores, no flashing commands, no dangerous permissions, no `INTERNET` permission, and no ROM buildability claim.

## Next phases

- Phase 11 — Build/test APK on Windows.
- Phase 12 — Lisa base ROM candidate selection.
- Phase 13 — Stock restore package verification.
- Phase 14 — Base ROM smoke flash planning.
- Phase 15 — Aelita preloaded/system app integration draft.
- Phase 16 — Alpha 0.1 build candidate review.
- Phase 17 — Controlled install test.

## Long-term phases

- Privileged system app design after non-privileged Alpha validation.
- Google replacement defaults after safe launcher/system-app behavior is verified.
- Notification and usage observation layers only with explicit user-granted access.
- Local Mind integration without external AI APIs.
- Framework/SystemUI hooks only after audited rollback paths exist.
- Daily-driver hardening only after stability, battery, privacy, and rollback are proven.
