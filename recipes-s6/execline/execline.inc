DESCRIPTION = "Skarnet execline"
HOMEPAGE = "http://skarnet.org/software/${PN}/"
LICENSE = "ISC"
SECTION = "base"
DEPENDS = "skalibs"
#RDEPENDS = "skalibs"
BBCLASSEXTEND = "native"

SRC_URI = "http://skarnet.org/software/execline/execline-${PV}.tar.gz"

#inherit autotools

# Force utilities to be in /bin, independant of what yocto thinks. (lots of
# scripts depend on having a /bin/execlineb shebang line).
bindir = "${base_prefix}/bin"

do_configure() {
	${S}/configure --enable-shared --enable-static --with-sysdeps=${STAGING_DIR_TARGET}${prefix}/lib/skalibs/sysdeps --prefix=${prefix} --bindir=${bindir}
    touch ${S}/-lskarnet
}

do_install () {
    oe_runmake install DESTDIR=${D}
}
