DESCRIPTION = "Skarnet base libraries"
HOMEPAGE = "http://skarnet.org/software/${PN}/"
LICENSE = "ISC"
SECTION = "base"
DEPENDS = "skalibs execline s6 s6-dns"
#RDEPENDS = "skalibs"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7f013666c13fc6c8d0127687dbfa63b"

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

# 2.3.0.2 needs:
#   skalibs >= 2.6.0.0
#   execline >= 2.3.0.2
#   s6 >= 2.6.1.0
#   s6-dns >= 2.2.0.1
#   optionally libressl >= 2.4.5  -or-  bearssl >= 0.5

# TODO: support building SSL/TLS support

SRC_URI = "http://skarnet.org/software/${PN}/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "3fa386e55922acc5a44922a39702a538"
SRC_URI[sha256sum] = "d81968756abbcecde6a872c89d82f643cd621a0c295c2c57650705dbe875481a"

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
