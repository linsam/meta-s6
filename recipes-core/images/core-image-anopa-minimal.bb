#IMAGE_INSTALL = "mingetty util-linux-agetty strace s6-initscript s6-networking s6-portable-utils s6-linux-utils s6-initscript anopa"
IMAGE_INSTALL = "mingetty util-linux-agetty strace s6-networking s6-portable-utils s6-linux-utils anopa-baselayout anopa coreutils gdb gnupg bash busybox"
IMAGE_LINGUAS = " "

inherit core-image
