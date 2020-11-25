DESCRIPTION = "Skarnet base libraries"
HOMEPAGE = "http://skarnet.org/software/${PN}/"
LICENSE = "ISC"
SECTION = "base"
DEPENDS = "skalibs execline s6 s6-dns"
#RDEPENDS = "skalibs"
LIC_FILES_CHKSUM = "file://COPYING;md5=2875ff3bd035b06bc171bb0e55a5f228"

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

# 2.3.0.3 needs:
#   skalibs >= 2.7.0.0
#   execline >= 2.5.0.1
#   s6 >= 2.7.2.0
#   s6-dns >= 2.3.0.1
#   optionally libressl >= 2.7.4  -or-  bearssl >= 0.5
#   Optionally nsss

# 2.3.2.0 needs:
#   skalibs >= 2.9.3.0
#   execline >= 2.6.1.1
#   s6 >= 2.9.2.0
#   s6-dns >= 2.3.3.0
#   optionally libressl >= 3.1.4  -or-  bearssl >= 0.6

# TODO: support building SSL/TLS support

SRC_URI = "http://skarnet.org/software/${PN}/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "255dd15ceb055cf5c9135581a70b8ee4"
SRC_URI[sha256sum] = "bbe36a8460d90f3bff56c934811876186f7224ced5bdc15c2c96d49b4e917d12"

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
