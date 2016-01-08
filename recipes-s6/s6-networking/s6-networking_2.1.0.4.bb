DESCRIPTION = "Skarnet base libraries"
HOMEPAGE = "http://skarnet.org/software/${PN}/"
LICENSE = "ISC"
SECTION = "base"
DEPENDS = "skalibs execline s6 s6-dns"
#RDEPENDS = "skalibs"
LIC_FILES_CHKSUM = "file://COPYING;md5=1500f33d86c4956999052c0e137cd652"

SRC_URI = "http://skarnet.org/software/${PN}/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "07a19e69db4978ef5c03cfd7daf3a827"
SRC_URI[sha256sum] = "0fb4bd02371077c6bed2878d22c9acbfc01834da6752277fbb92d8312076640a"

do_configure() {
	${S}/configure --enable-shared --enable-static --with-sysdeps=${STAGING_DIR_TARGET}/usr/lib/skalibs/sysdeps
    touch ${S}/-lskarnet
    touch ${S}/-lexecline
    touch ${S}/-ls6
    touch ${S}/-ls6dns
}

do_install () {
    oe_runmake install DESTDIR=${D}
}
