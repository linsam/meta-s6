require s6-linux-utils.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=682e28b5c36e2b48d5b0cf2fbe8990f5"

SRC_URI[md5sum] = "d2c21e88b29094ca591f1f4c4bf8890f"
SRC_URI[sha256sum] = "78571574464c76fc623ef50dc14800138b4fd98c5fa877af6850fdd9c2f90213"

# 2.2.0.0 needs skalibs >= 2.4.0.0
# 2.4.0.0 needs skalibs >= 2.5.1.0
# 2.4.0.2 needs skalibs >= 2.6.0.2
# 2.5.0.0 needs skalibs >= 2.7.0.0, adds optional nsss (not supported by us yet)

# halt, poweroff, and reboot moved to s6-init-maker package
# devd, uevent-listener, uevent-spawner removed, functionality replaced by mdevd
ALTERNATIVE_${PN} = "chroot freeramdisk hostname logwatch mount pivotchroot ps swapoff swapon umount"
