DESCRIPTION = "Skarnet base libraries"
HOMEPAGE = "http://skarnet.org/software/${PN}/"
LICENSE = "ISC"
SECTION = "base"
DEPENDS = "skalibs"
RDEPENDS_${PN} = "skalibs"

SRC_URI = "http://skarnet.org/software/${PN}/${PN}-${PV}.tar.gz"

do_configure() {
	${S}/configure --enable-shared --enable-static --with-sysdeps=${STAGING_DIR_TARGET}/usr/lib/skalibs/sysdeps
    touch ${S}/-lskarnet
}

do_install () {
    oe_runmake install DESTDIR=${D}
}

ALTERNATIVE_${PN} = "chroot devd freeramdisk halt hostname logwatch mount pivotchroot poweroff ps reboot swapoff swapon uevent-listener uevent-spawner umount"
# Vim script to convert a list of utilities into the alternates stuffs below.
# Useful if the list ever needs updating.
# 'a,'bg/./exe "normal d$iALTERNATIVE_LINK_NAME["] = \"/usr/bin/"\" ALTERNATIVE_TARGET["] = \"/usr/bin/s6-"\""
#
ALTERNATIVE_LINK_NAME[chroot] = "/usr/bin/chroot"
ALTERNATIVE_TARGET[chroot] = "/usr/bin/s6-chroot"
ALTERNATIVE_LINK_NAME[devd] = "/usr/bin/devd"
ALTERNATIVE_TARGET[devd] = "/usr/bin/s6-devd"
ALTERNATIVE_LINK_NAME[freeramdisk] = "/usr/bin/freeramdisk"
ALTERNATIVE_TARGET[freeramdisk] = "/usr/bin/s6-freeramdisk"
ALTERNATIVE_LINK_NAME[halt] = "/usr/bin/halt"
ALTERNATIVE_TARGET[halt] = "/usr/bin/s6-halt"
ALTERNATIVE_LINK_NAME[hostname] = "/usr/bin/hostname"
ALTERNATIVE_TARGET[hostname] = "/usr/bin/s6-hostname"
ALTERNATIVE_LINK_NAME[logwatch] = "/usr/bin/logwatch"
ALTERNATIVE_TARGET[logwatch] = "/usr/bin/s6-logwatch"
ALTERNATIVE_LINK_NAME[mount] = "/usr/bin/mount"
ALTERNATIVE_TARGET[mount] = "/usr/bin/s6-mount"
ALTERNATIVE_LINK_NAME[pivotchroot] = "/usr/bin/pivotchroot"
ALTERNATIVE_TARGET[pivotchroot] = "/usr/bin/s6-pivotchroot"
ALTERNATIVE_LINK_NAME[poweroff] = "/usr/bin/poweroff"
ALTERNATIVE_TARGET[poweroff] = "/usr/bin/s6-poweroff"
ALTERNATIVE_LINK_NAME[ps] = "/usr/bin/ps"
ALTERNATIVE_TARGET[ps] = "/usr/bin/s6-ps"
ALTERNATIVE_LINK_NAME[reboot] = "/usr/bin/reboot"
ALTERNATIVE_TARGET[reboot] = "/usr/bin/s6-reboot"
ALTERNATIVE_LINK_NAME[swapoff] = "/usr/bin/swapoff"
ALTERNATIVE_TARGET[swapoff] = "/usr/bin/s6-swapoff"
ALTERNATIVE_LINK_NAME[swapon] = "/usr/bin/swapon"
ALTERNATIVE_TARGET[swapon] = "/usr/bin/s6-swapon"
ALTERNATIVE_LINK_NAME[uevent-listener] = "/usr/bin/uevent-listener"
ALTERNATIVE_TARGET[uevent-listener] = "/usr/bin/s6-uevent-listener"
ALTERNATIVE_LINK_NAME[uevent-spawner] = "/usr/bin/uevent-spawner"
ALTERNATIVE_TARGET[uevent-spawner] = "/usr/bin/s6-uevent-spawner"
ALTERNATIVE_LINK_NAME[umount] = "/usr/bin/umount"
ALTERNATIVE_TARGET[umount] = "/usr/bin/s6-umount"

ALTERNATIVE_PRIORITY = "5"

inherit update-alternatives
