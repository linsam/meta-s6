DESCRIPTION = "Skarnet base libraries"
HOMEPAGE = "http://skarnet.org/software/${PN}/"
LICENSE = "ISC"
SECTION = "base"
DEPENDS = "skalibs execline s6 s6-dns"
#RDEPENDS = "skalibs"
LIC_FILES_CHKSUM = "file://COPYING;md5=6ff3b66afd85801a2be9d46214f4e7b5"

# 2.2.1.0 needs:
#  skalibs >= 2.4.0.2
#  execline >= 2.2.0.0
#  s6 >= 2.4.0.0
#  s6-dns >= 2.1.0.0
#  optionally libressl >= 2.4.4   -or-  bearssl >= 0.1
#
#  SSL/TLS support is new in version 2.2.1.0.

# TODO: support building SSL/TLS support

SRC_URI = "http://skarnet.org/software/${PN}/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "ba0fa36fa820692aa0923d534fd82445"
SRC_URI[sha256sum] = "5d8150d7413b335693c6e63092381dd0866b1b6f95662a2cbae5ec17ca449ab0"

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
