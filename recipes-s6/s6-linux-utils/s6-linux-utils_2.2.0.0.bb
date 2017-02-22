require s6-linux-utils.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6ff3b66afd85801a2be9d46214f4e7b5"

SRC_URI[md5sum] = "70b09e0d1b498cf647eea491a1969033"
SRC_URI[sha256sum] = "587745abfbf2cc631bb54dd2b3fd29f39b723e3c50e28165138a85c98fda35f9"

# 2.2.0.0 needs skalibs >= 2.4.0.0

# halt, poweroff, and reboot moved to s6-init-maker package
ALTERNATIVE_${PN} = "chroot devd freeramdisk hostname logwatch mount pivotchroot ps swapoff swapon uevent-listener uevent-spawner umount"
