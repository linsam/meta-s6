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
