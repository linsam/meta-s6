DESCRIPTION = "Skarnet base libraries"
HOMEPAGE = "http://skarnet.org/software/${PN}/"
LICENSE = "ISC"
SECTION = "base"
DEPENDS = "skalibs execline"
RDEPENDS_${PN} = "skalibs"
PR = "r2"
BBCLASSEXTEND = "native"

SRC_URI = "http://skarnet.org/software/s6/s6-${PV}.tar.gz"

bindir="${base_prefix}/bin"

do_configure() {
	${S}/configure --enable-shared --enable-static --with-sysdeps=${STAGING_DIR_TARGET}${prefix}/lib/skalibs/sysdeps --prefix=${prefix} --bindir=${bindir}
    touch ${S}/-lskarnet
    touch ${S}/-lexecline
}

do_install () {
    oe_runmake install DESTDIR=${D}
}

FILES_${PN} += " /libexec"
