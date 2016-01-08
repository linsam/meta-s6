DESCRIPTION = "Skarnet base libraries"
HOMEPAGE = "http://skarnet.org/software/${PN}/"
LICENSE = "ISC"
SECTION = "base"
DEPENDS = "skalibs"
#RDEPENDS = "skalibs"
LIC_FILES_CHKSUM = "file://COPYING;md5=1500f33d86c4956999052c0e137cd652"

SRC_URI = "http://skarnet.org/software/${PN}/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "09612618faf23e9981597ce634742194"
SRC_URI[sha256sum] = "27be01b3d66df617ea7dbc21b55d253d5b9142463099f6944f90ef33587a2e09"

do_configure() {
	${S}/configure --enable-shared --enable-static --with-sysdeps=${STAGING_DIR_TARGET}/usr/lib/skalibs/sysdeps
    touch ${S}/-lskarnet
}

do_install () {
    oe_runmake install DESTDIR=${D}
}
