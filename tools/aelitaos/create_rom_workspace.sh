#!/usr/bin/env bash
set -eu

WORKSPACE_DIR="${1:-${AELITAOS_ROM_WORKSPACE:-$HOME/aelitaos-lisa-rom}}"

printf 'Creating AelitaOS lisa external ROM workspace layout only.\n'
printf 'No source download, repo init/sync, adb, fastboot, flashing, sudo, or package install will be run.\n\n'

for path in \
  "$WORKSPACE_DIR" \
  "$WORKSPACE_DIR/aosp" \
  "$WORKSPACE_DIR/local_manifests" \
  "$WORKSPACE_DIR/out" \
  "$WORKSPACE_DIR/logs" \
  "$WORKSPACE_DIR/artifacts" \
  "$WORKSPACE_DIR/notes"; do
  mkdir -p "$path"
  printf 'READY: %s\n' "$path"
done

printf '\nWorkspace scaffold is ready for later reviewed source setup: %s\n' "$WORKSPACE_DIR"
printf 'SAFE_TO_FLASH=no\n'
