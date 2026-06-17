# Base ROM Candidate Checklist

Этот чеклист используется для любой возможной базы AelitaOS на Xiaomi 11 Lite 5G NE (`lisa`). Кандидат не считается пригодным, пока пункты не проверены и не записаны.

## Обязательные проверки

- [ ] Exact device codename is `lisa`.
- [ ] Source link recorded.
- [ ] Maintainer/source trust checked.
- [ ] Android version recorded.
- [ ] Firmware requirement recorded.
- [ ] Recovery requirement recorded.
- [ ] Installation method recorded.
- [ ] Known bugs recorded.
- [ ] Rollback method recorded.
- [ ] Stock restore package available.
- [ ] No bootloader relock required.
- [ ] No random cross-device build.
- [ ] No unknown vendor/kernel mix.
- [ ] No unsupported partition layout assumptions.
- [ ] No "works on similar Xiaomi" guesses.

## Decision rule

Если хотя бы один критический пункт неизвестен, кандидат остается в статусе research-only и не становится базой AelitaOS.
