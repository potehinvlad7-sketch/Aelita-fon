# Aelita-Fon ROM Preload Layout Draft

This draft records possible future placement options for Aelita-Fon in an AelitaOS Alpha 0.1 candidate. It is not a ROM build recipe and does not claim that this repository can build a flashable ROM.

## Option A — preloaded normal app

- Install as a normal preloaded app.
- Safer for Alpha 0.1.
- No privileged permissions.
- Best first integration target because behavior remains closest to the APK smoke test.

## Option B — system app

- Place as a system app.
- Still no privileged permissions.
- Useful for launcher availability and default app experiments.
- Requires separate ROM packaging review before use.

## Option C — priv-app

- Place as a privileged app.
- Not for Alpha 0.1.
- Requires later explicit permission review.
- Must not be used until privileged permission needs, risks, and rollback paths are audited.

## Alpha 0.1 recommendation

Use Option A or Option B for Alpha 0.1 planning.

Do not use `priv-app` yet.
