DESCRIPTION = "Skarnet base libraries"
HOMEPAGE = "http://skarnet.org/software/${PN}/"
LICENSE = "ISC"
SECTION = "base"
DEPENDS = "skalibs"
#RDEPENDS = "skalibs"
LIC_FILES_CHKSUM = "file://COPYING;md5=1500f33d86c4956999052c0e137cd652"

SRC_URI = "http://skarnet.org/software/${PN}/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "e4ae28e1ca13d9cd7a226a2729c71bbf"
SRC_URI[sha256sum] = "f959ffb9bb79865018becc6664d29faef22cb747a43db252879e11886b1b8cc3"

do_configure() {
	${S}/configure --enable-shared --enable-static --with-sysdeps=${STAGING_DIR_TARGET}/usr/lib/skalibs/sysdeps
    touch ${S}/-lskarnet
}

do_install () {
    oe_runmake install DESTDIR=${D}
}
