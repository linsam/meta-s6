DESCRIPTION = "Skarnet service manager"
HOMEPAGE = "http://skarnet.org/software/${PN}/"
LICENSE = "ISC"
SECTION = "base"
DEPENDS = "skalibs execline s6"
RDEPENDS_${PN} = "skalibs execline s6"

SRC_URI = "http://skarnet.org/software/${PN}/${PN}-${PV}.tar.gz"

bindir="/bin"

do_configure() {
	${S}/configure --enable-shared --enable-static --with-sysdeps=${STAGING_DIR_TARGET}/usr/lib/skalibs/sysdeps
    touch ${S}/-lskarnet
    touch ${S}/-lexecline
    touch ${S}/-ls6
}

do_install () {
    oe_runmake install DESTDIR=${D}
}
