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
#
# 2.3.0.1 needs:
#   skalibs >= 2.5.1.0
#   execline >= 2.3.0.1
#   s6 >= 2.5.1.0
#   s6-dns >= 2.2.0.0
#   optionally libressl >= 2.4.5  -or-  bearssl >= 0.4

# TODO: support building SSL/TLS support

SRC_URI = "http://skarnet.org/software/${PN}/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "fb977fbddfb43f511bf56bdef54a0a27"
SRC_URI[sha256sum] = "67c4beab39377b60bb996000a336c764adf4b5456c0283a8fd27657b15e9c6d6"

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
