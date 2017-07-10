DESCRIPTION = "Linux init utils"
HOMEPAGE = "http://skarnet.org/software/${PN}/"
LICENSE = "ISC"
SECTION = "base"
DEPENDS = "skalibs"
RDEPENDS_${PN} = "skalibs"

BBCLASSEXTEND = "native"

SRC_URI = "http://skarnet.org/software/s6-linux-init/s6-linux-init-${PV}.tar.gz"

do_configure() {
	${S}/configure --enable-shared --enable-static --with-sysdeps=${STAGING_DIR_TARGET}${base_prefix}/usr/lib/skalibs/sysdeps --prefix=${prefix}
    touch ${S}/-lskarnet
}

do_install () {
    oe_runmake install DESTDIR=${D}
}

ALTERNATIVE_${PN} = "halt poweroff reboot"
# Vim script to convert a list of utilities into the alternates stuffs below.
# Useful if the list ever needs updating.
# 'a,'bg/./exe "normal d$iALTERNATIVE_LINK_NAME["] = \"/usr/bin/"\" ALTERNATIVE_TARGET["] = \"/usr/bin/s6-"\""
#
ALTERNATIVE_LINK_NAME[halt] = "/usr/bin/halt"
ALTERNATIVE_TARGET[halt] = "/usr/bin/s6-halt"
ALTERNATIVE_LINK_NAME[poweroff] = "/usr/bin/poweroff"
ALTERNATIVE_TARGET[poweroff] = "/usr/bin/s6-poweroff"
ALTERNATIVE_LINK_NAME[reboot] = "/usr/bin/reboot"
ALTERNATIVE_TARGET[reboot] = "/usr/bin/s6-reboot"

ALTERNATIVE_PRIORITY = "5"

inherit update-alternatives
