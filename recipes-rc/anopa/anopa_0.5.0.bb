DESCRIPTION = "Anopa rc"
HOMEPAGE = "http://jjacky.com/anopa/"
LICENSE = "GPLv3+"
SECTION = "base"
DEPENDS = "skalibs (< 2.6) execline s6 (< 2.7)"
RDEPENDS_${PN} = "execline anopa-common"
RDEPENDS_${PN}-initrd = "execline anopa-common"
RDEPENDS_${PN}-common = "skalibs (< 2.6) execline s6 (< 2.7)"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

# Anopa 0.5.0 requires
#   skalibs >= 2.5.0.0
#   execline >= 2.3.0.1
#   s6 >= 2.5.0.0

SRC_URI = "http://jjacky.com/anopa/anopa-0.5.0.tar.xz \
          "
SRC_URI[md5sum] = "301cb26a9ed5c9575cf23331a572c6fa"
SRC_URI[sha256sum] = "a9f3f7024220c665e11853cde6371907d92752b73bf3524ef1f3746b51705d89"

# TODO: looks like the stage scripts moved? Now installed to /libexec or
# somesuch. System no longer boots.

do_configure() {
	${S}/configure --enable-shared --enable-static --with-sysdeps=${STAGING_DIR_TARGET}/${libdir}/skalibs/sysdeps --with-lib=${STAGING_DIR_TARGET}${libdir} --with-dynlib=${STAGING_DIR_TARGET}${libdir} --exec-prefix=/
    touch ${S}/-lskarnet
    touch ${S}/-ls6
    touch ${S}/-lexecline
}

do_install () {
    oe_runmake install DESTDIR=${D}
}

PACKAGES+="${PN}-initrd ${PN}-common"

FILES_${PN} = "/libexec/aa-stage1 /libexec/aa-stage2 /libexec/aa-stage3"
FILES_${PN}-initrd = "/libexec/aa-stage0 /libexec/aa-stage4"
FILES_${PN}-common = "/lib /bin"
