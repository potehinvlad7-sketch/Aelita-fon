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

## Current phase

## Phase 14 — AelitaOS ROM workspace initialized — this PR

- Create documentation-only AelitaOS ROM workspace folders.
- Create `lisa`-only device workspace placeholders.
- Define Alpha 0.1 build ladder without claiming buildability.
- Define rollback and first ROM test scope before any flash attempt.
- Keep the PR safe: no APKs, no ZIPs, no IMGs, no binaries, no vendor blobs, no kernel/recovery images, no signing keys, no flashing scripts, no flashing commands, no dangerous permissions, and no Alpha 0.1 flashability claim.

## Next phases

- Phase 15 — Lisa base ROM/source candidate selection.
- Phase 16 — Stock restore package verification.
- Phase 17 — Build host setup.
- Phase 18 — Lisa device tree/vendor/kernel source review.
- Phase 19 — Base ROM build attempt.
- Phase 20 — Base ROM smoke flash review.
- Phase 21 — Aelita-Fon system app integration.
- Phase 22 — AelitaOS Alpha 0.1 build candidate.
- Phase 23 — Controlled install test.

## Long-term phases

- Privileged system app design after non-privileged Alpha validation.
- Google replacement defaults after safe launcher/system-app behavior is verified.
- Notification and usage observation layers only with explicit user-granted access.
- Local Mind integration without external AI APIs.
- Framework/SystemUI hooks only after audited rollback paths exist.
- Daily-driver hardening only after stability, battery, privacy, and rollback are proven.
