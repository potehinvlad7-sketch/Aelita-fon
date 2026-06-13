# ROM Base Research for Alpha 0.1

## Option A: known working lisa custom ROM release

- Fastest to test.
- Lower source-build burden.
- Must verify maintainer trust, exact codename, known bugs, firmware requirement, and rollback path.

## Option B: LineageOS-like source build

- Cleaner long-term AelitaOS path.
- Requires Linux, WSL2, or native Linux build host.
- Needs device tree, vendor blobs, and kernel strategy.

## Option C: GSI experiment

- Fastest generic Treble experiment.
- Weaker device integration.
- Not ideal as final AelitaOS path.
- May have hardware bugs.

## Option D: stock HyperOS-based modification

- Strong hardware compatibility.
- Harder and riskier to modify cleanly.
- Not preferred for Alpha 0.1.

## Recommendation

For Alpha 0.1, use a known working `lisa` Android-based ROM base or known source tree first, then integrate Aelita-Fon as a preinstalled app/system app candidate.

Do not begin with raw AOSP without a verified `lisa` device tree, vendor blobs, and kernel strategy.
