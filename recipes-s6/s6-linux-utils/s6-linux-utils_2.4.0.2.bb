require s6-linux-utils.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=d7f013666c13fc6c8d0127687dbfa63b"

SRC_URI[md5sum] = "e845c21f7297851f1dd49f3b63d18832"
SRC_URI[sha256sum] = "37e2ad9ae370af39d1a2ae4bbf26a778539e6fb4c09d47a276de3b7e66cd8508"

# 2.2.0.0 needs skalibs >= 2.4.0.0
# 2.4.0.0 needs skalibs >= 2.5.1.0
# 2.4.0.2 needs skalibs >= 2.6.0.2

# halt, poweroff, and reboot moved to s6-init-maker package
ALTERNATIVE_${PN} = "chroot devd freeramdisk hostname logwatch mount pivotchroot ps swapoff swapon uevent-listener uevent-spawner umount"
