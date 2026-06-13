# Device Tree Requirements for lisa

A future source-build path needs the following components before it can be considered practical:

- Device tree for `lisa`.
- Vendor blobs.
- Kernel source or prebuilt kernel strategy.
- Firmware requirements.
- Recovery strategy.
- Local manifest.
- Product makefiles.
- SELinux policy status.
- Proprietary-files extraction path.
- Known broken hardware list.
- Maintainer trust notes.

Third-party trees must be audited before use. The audit should check device identity, commit history, maintainer reputation, open issues, firmware assumptions, security policy changes, and whether the tree has been used successfully on `lisa`.
