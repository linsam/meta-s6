DESCRIPTION = "Skarnet service manager"
HOMEPAGE = "http://skarnet.org/software/${PN}/"
LICENSE = "ISC"
SECTION = "base"
DEPENDS = "skalibs execline s6"
RDEPENDS_${PN} = "skalibs execline s6"

BBCLASSEXTEND = "native"

SRC_URI = "http://skarnet.org/software/s6-rc/s6-rc-${PV}.tar.gz"

bindir="${base_prefix}/bin"

do_configure() {
    # ${base_prefix} instead of ${STAGING_DIR_TARGET} for compat when doing
    # native?
	${S}/configure --enable-shared --enable-static --with-sysdeps=${STAGING_DIR_TARGET}${base_prefix}/usr/lib/skalibs/sysdeps --prefix=${prefix} --bindir=${bindir}
    touch ${S}/-lskarnet
    touch ${S}/-lexecline
    touch ${S}/-ls6
}

do_install () {
    oe_runmake install DESTDIR=${D}
}

FILES_${PN} += " /libexec/"
