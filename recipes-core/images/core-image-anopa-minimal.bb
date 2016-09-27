IMAGE_INSTALL = "\
    anopa \
    anopa-baselayout \
    busybox \
    s6-linux-utils \
    s6-networking \
    s6-portable-utils \
    util-linux-agetty \
    "
IMAGE_LINGUAS = " "

IMAGE_ROOTFS_SIZE="15000"

inherit core-image
