require s6-linux-utils.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=2875ff3bd035b06bc171bb0e55a5f228"

SRC_URI[md5sum] = "4e3cbdcb21dc68b890790fd15321a3cd"
SRC_URI[sha256sum] = "4471511c9ce995c5ac61e0714def5a05fcabe730ef0bb93a42b12ad5bf007b71"

# 2.2.0.0 needs skalibs >= 2.4.0.0
# 2.4.0.0 needs skalibs >= 2.5.1.0
# 2.4.0.2 needs skalibs >= 2.6.0.2
# 2.5.0.0 needs skalibs >= 2.7.0.0, adds optional nsss (not supported by us yet)
# 2.5.1.3 needs skalibs >= 2.9.3.0

# halt, poweroff, and reboot moved to s6-init-maker package
# devd, uevent-listener, uevent-spawner removed, functionality replaced by mdevd
ALTERNATIVE_${PN} = "chroot freeramdisk hostname logwatch mount pivotchroot ps swapoff swapon umount"
