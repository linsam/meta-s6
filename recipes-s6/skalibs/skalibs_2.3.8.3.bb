DESCRIPTION = "Skarnet base libraries"
HOMEPAGE = "http://skarnet.org/software/${PN}/"
LICENSE = "ISC"
SECTION = "base"
DEPENDS = ""
LIC_FILES_CHKSUM = "file://COPYING;md5=1500f33d86c4956999052c0e137cd652"

SRC_URI = "http://skarnet.org/software/${PN}/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "142a296760aab02f2821abf00706e53a"
SRC_URI[sha256sum] = "3652e268f42ce60959dcc80ca3eba8dc17b6d365309179bfb0e04bc5f1603d7c"

#inherit autotools

do_configure() {
    # Not sure why, but package and package_macro_name seem to be unset when
    # crossing sometimes (e.g. for raspberry pi)
    package=skalibs package_macro_name=SKALIBS ${S}/configure --enable-force-devr
}

do_install () {
    oe_runmake install DESTDIR=${D}
}
