#!/usr/bin/env bash
set -u

FAILURES=0

check_file() {
  local path="$1"
  if [ -f "$path" ]; then
    printf 'PASS: %s exists\n' "$path"
  else
    printf 'FAIL: %s is missing\n' "$path"
    FAILURES=$((FAILURES + 1))
  fi
}

printf 'Validating AelitaOS ROM scaffold only.\n'
printf 'This does not build, download, run adb, run fastboot, or flash anything.\n\n'

check_file rom/aelitaos/manifests/aelita_lisa_local_manifest.xml.template
check_file rom/aelitaos/products/aelita_lisa.mk.template
check_file rom/aelitaos/products/AndroidProducts.mk.template
check_file vendor/aelita/apps/aelita_fon_preload_plan.mk.template
check_file tools/aelitaos/check_build_host.sh
check_file tools/aelitaos/create_rom_workspace.sh

printf '\nREADY_FOR_ROM_BUILD=no\n'
printf 'READY_FOR_FLASH=no\n'

if [ "$FAILURES" -eq 0 ]; then
  printf 'Scaffold validation passed.\n'
  exit 0
fi

printf 'Scaffold validation failed with %s missing file(s).\n' "$FAILURES"
exit 1
