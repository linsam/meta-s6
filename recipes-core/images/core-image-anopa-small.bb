# Is pam doing anything? Need to look into it.

IMAGE_INSTALL = "\
    alsa-utils \
    anopa \
    anopa-baselayout \
    at \
    bash \
    busybox \
    coreutils \
    cronie \
    curl \
    debianutils \
    dropbear \
    e2fsprogs \
    ed \
    findutils \
    gawk \
    gdb \
    gnupg \
    gnutls \
    gzip \
    iproute2 \
    iptables \
    libpam \
    lsof \
    parted \
    pciutils \
    procps \
    psmisc \
    s6-networking \
    sed \
    strace \
    sudo \
    sysfsutils \
    sysstat \
    util-linux \
    util-linux-agetty \
    xz \
    zip \
    "
IMAGE_LINGUAS = " "

DISTRO_FEATURES += " pam"

IMAGE_ROOTFS_SIZE="15000"

inherit core-image