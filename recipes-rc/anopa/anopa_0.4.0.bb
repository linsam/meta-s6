DESCRIPTION = "Anopa rc"
HOMEPAGE = "http://jjacky.com/anopa/"
LICENSE = "GPLv3+"
SECTION = "base"
DEPENDS = "skalibs execline s6"
RDEPENDS_${PN} = "execline"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "http://jjacky.com/anopa/anopa-0.4.0.tar.gz \
           file://version-0.4.0.patch \
          "
SRC_URI[md5sum] = "d9262f054db4240718f8a43663c8f481"
SRC_URI[sha256sum] = "02987f834b77c1b9c3f06ec2e9ed9b705c219d549c016231c65eaca18639ef5a"

do_configure() {
	${S}/configure --enable-shared --enable-static --with-sysdeps=${STAGING_DIR_TARGET}/${libdir}/skalibs/sysdeps
    touch ${S}/-lskarnet
    touch ${S}/-ls6
    touch ${S}/-lexecline
}

do_install () {
    oe_runmake install DESTDIR=${D}
}
