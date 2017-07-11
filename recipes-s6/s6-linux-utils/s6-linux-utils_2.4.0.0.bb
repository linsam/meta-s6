require s6-linux-utils.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6ff3b66afd85801a2be9d46214f4e7b5"

SRC_URI[md5sum] = "1c3edc496d6c580416c96a871f2bb74c"
SRC_URI[sha256sum] = "3afb7b972a9d54042ac0aabde5489be90039374b6a90ef45f7f32ce2e83d1e63"

# 2.2.0.0 needs skalibs >= 2.4.0.0
# 2.4.0.0 needs skalibs >= 2.5.1.0

# halt, poweroff, and reboot moved to s6-init-maker package
ALTERNATIVE_${PN} = "chroot devd freeramdisk hostname logwatch mount pivotchroot ps swapoff swapon uevent-listener uevent-spawner umount"
