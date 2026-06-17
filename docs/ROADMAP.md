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
- Phase 11 — Build/test APK on Windows — done in practice.
- Phase 12 — Debug APK smoke test on real `lisa` — done in practice.
- Phase 13 — Debug APK CI hardening — done.
- Phase 14 — AelitaOS ROM workspace initialized — done.
- Phase 15 — ROM file map and clean base strategy — done.

## Current phase

## Phase 16 — Lisa clean ROM/source candidate research — this PR

- Research real base ROM/source candidates with explicit Xiaomi 11 Lite 5G NE / `lisa` support.
- Record official and source-tree candidates without downloading packages or adding binaries.
- Create a shortlist that keeps source-build, reference-ROM and GSI paths separate.
- Keep the PR safe: no APKs, no ZIPs, no IMGs, no binaries, no vendor blobs, no kernel/recovery images, no signing keys, no flashing scripts, no flashing commands, no dangerous permissions, no ROM buildability claim, and no Alpha 0.1 flashability claim.

## Next phases

- Phase 17 — Stock restore package verification.
- Phase 18 — Build host setup.
- Phase 19 — Lisa device tree/vendor/kernel source review.
- Phase 20 — Base ROM build attempt.
- Phase 21 — Base ROM smoke flash review.
- Phase 22 — Aelita-Fon system app integration.
- Phase 23 — AelitaOS Alpha 0.1 build candidate.
- Phase 24 — Controlled install test.

## Long-term phases

- Privileged system app design after non-privileged Alpha validation.
- Google replacement defaults after safe launcher/system-app behavior is verified.
- Notification and usage observation layers only with explicit user-granted access.
- Local Mind integration without external AI APIs.
- Framework/SystemUI hooks only after audited rollback paths exist.
- Daily-driver hardening only after stability, battery, privacy, and rollback are proven.
