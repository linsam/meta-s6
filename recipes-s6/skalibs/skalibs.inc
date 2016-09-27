DESCRIPTION = "Skarnet base libraries"
HOMEPAGE = "http://skarnet.org/software/${PN}/"
LICENSE = "ISC"
SECTION = "base"
DEPENDS = ""

SRC_URI = "http://skarnet.org/software/${PN}/${PN}-${PV}.tar.gz"

#inherit autotools

do_configure() {
    # Not sure why, but package and package_macro_name seem to be unset when
    # crossing sometimes (e.g. for raspberry pi)
    package=skalibs package_macro_name=SKALIBS ${S}/configure --enable-force-devr
}

do_install () {
    oe_runmake install DESTDIR=${D}
}

