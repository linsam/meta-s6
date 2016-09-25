# Based on meta/recipes-core/images/core-image-minimal-initramfs.bb
PACKAGE_INSTALL = "busybox anopa-initrd anopa-baselayout-initrd base-passwd ${ROOTFS_BOOTSTRAP_INSTALL}"
#PACKAGE_INSTALL = "busybox anopa-initrd base-passwd ${ROOTFS_BOOTSTRAP_INSTALL}"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

export IMAGE_BASENAME = "core-image-anopa-minimal-initramfs"
IMAGE_LINGUAS = ""

LICENSE = "MIT"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit core-image

IMAGE_ROOTFS_SIZE = "6200"

BAD_RECOMMENDATIONS += "busybox-syslog busybox-udhcpc"
