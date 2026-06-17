#!/usr/bin/env bash
set -u

WORKSPACE_DIR="${1:-${AELITAOS_ROM_WORKSPACE:-$HOME/aelitaos-lisa-rom}}"
FAILURES=0
WARNINGS=0

pass() { printf 'PASS: %s\n' "$1"; }
warn() { printf 'WARN: %s\n' "$1"; WARNINGS=$((WARNINGS + 1)); }
fail() { printf 'FAIL: %s\n' "$1"; FAILURES=$((FAILURES + 1)); }

check_command() {
  local name="$1"
  if command -v "$name" >/dev/null 2>&1; then
    pass "$name exists ($(command -v "$name"))"
  else
    fail "$name is missing"
  fi
}

printf 'AelitaOS lisa ROM build host check\n'
printf 'Target workspace: %s\n\n' "$WORKSPACE_DIR"

case "$(uname -s 2>/dev/null || printf unknown)" in
  Linux)
    pass "OS type is Linux"
    if grep -qi microsoft /proc/version 2>/dev/null; then
      warn "WSL detected; WSL2 can be used for preparation, but native Linux is preferred for full ROM builds"
    elif grep -qi wsl /proc/sys/kernel/osrelease 2>/dev/null; then
      warn "WSL-like kernel detected; verify WSL2 resources before source sync/build"
    else
      pass "Native Linux-like environment detected"
    fi
    ;;
  *)
    fail "Unsupported OS type: $(uname -s 2>/dev/null || printf unknown); use Linux or WSL2"
    ;;
esac

check_command bash
check_command git
check_command python3
check_command curl
check_command unzip
check_command zip
check_command make

if command -v gcc >/dev/null 2>&1 || command -v clang >/dev/null 2>&1; then
  pass "C/C++ compiler present (gcc or clang)"
else
  warn "No gcc or clang found; Android builds usually need host compiler tooling"
fi

if command -v java >/dev/null 2>&1; then
  JAVA_VERSION_OUTPUT="$(java -version 2>&1 | head -n 1)"
  pass "Java exists: ${JAVA_VERSION_OUTPUT}"
  if printf '%s' "$JAVA_VERSION_OUTPUT" | grep -Eq '"(1\.8|8\.|11\.|17\.|21\.)'; then
    pass "Java version string looks plausible for Android build work; verify against selected ROM branch"
  else
    warn "Java version may not match the future selected Android branch; verify once the base is selected"
  fi
else
  fail "java is missing"
fi

AVAILABLE_KB="$(df -Pk "$HOME" 2>/dev/null | awk 'NR==2 {print $4}')"
if [ -n "${AVAILABLE_KB:-}" ]; then
  AVAILABLE_GB=$((AVAILABLE_KB / 1024 / 1024))
  if [ "$AVAILABLE_GB" -ge 300 ]; then
    pass "Free disk space near HOME is ${AVAILABLE_GB} GiB"
  elif [ "$AVAILABLE_GB" -ge 150 ]; then
    warn "Free disk space near HOME is ${AVAILABLE_GB} GiB; Android source/builds often need 300+ GiB"
  else
    fail "Free disk space near HOME is ${AVAILABLE_GB} GiB; too low for ROM source/build work"
  fi
else
  warn "Could not estimate free disk space"
fi

MEM_KB="$(awk '/MemTotal/ {print $2}' /proc/meminfo 2>/dev/null || true)"
if [ -n "${MEM_KB:-}" ]; then
  MEM_GB=$((MEM_KB / 1024 / 1024))
  if [ "$MEM_GB" -ge 16 ]; then
    pass "RAM estimate is ${MEM_GB} GiB"
  elif [ "$MEM_GB" -ge 8 ]; then
    warn "RAM estimate is ${MEM_GB} GiB; builds may be slow or fail without swap"
  else
    fail "RAM estimate is ${MEM_GB} GiB; likely too low for ROM builds"
  fi
else
  warn "Could not estimate RAM"
fi

CORES="$(getconf _NPROCESSORS_ONLN 2>/dev/null || nproc 2>/dev/null || printf 0)"
if [ "${CORES:-0}" -ge 8 ]; then
  pass "CPU cores available: $CORES"
else
  warn "CPU cores available: ${CORES:-unknown}; ROM builds may be slow"
fi

if command -v repo >/dev/null 2>&1; then
  pass "repo command exists ($(command -v repo))"
else
  warn "repo command is missing; needed later for source sync, but this script will not install or run it"
fi

if [ -d "$WORKSPACE_DIR" ]; then
  pass "Target workspace directory exists: $WORKSPACE_DIR"
else
  warn "Target workspace directory does not exist yet: $WORKSPACE_DIR"
fi

printf '\nREADY_FOR_SOURCE_SYNC=%s\n' "$([ "$FAILURES" -eq 0 ] && printf yes || printf no)"
printf 'READY_FOR_FLASH=no\n'
printf 'Summary: %s failure(s), %s warning(s).\n' "$FAILURES" "$WARNINGS"

if [ "$FAILURES" -eq 0 ]; then
  exit 0
fi
exit 1
