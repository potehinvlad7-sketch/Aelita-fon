# Future ROM Build Pipeline

This repo currently contains the Aelita Shell component and architecture documents. The ROM tree will be introduced in later PRs or a dedicated repo.

## High-level future pipeline

A real AelitaOS build pipeline will eventually require:

- host Linux build machine;
- `repo` tool and AOSP or Lineage-based source tree;
- device tree for `lisa`;
- vendor blobs;
- kernel source or prebuilt kernel depending on the selected base;
- local manifest;
- product makefiles;
- privileged app inclusion configuration;
- signing keys;
- build artifact;
- test flashing on target device;
- rollback images.

## Honesty requirement

This repository does not currently build a ROM. It does not contain a full Android source tree, device tree, vendor tree, kernel tree, signing setup or flashable artifacts.

## Safety requirement

This document intentionally does not include commands that assume a ready ROM tree. Exact build and flashing commands must only be added after the base tree, target device state and rollback path are verified.
