DESCRIPTION = "Anopa rc"
HOMEPAGE = "http://jjacky.com/anopa/"
LICENSE = "GPLv3+"
SECTION = "base"
DEPENDS = "skalibs execline s6"
RDEPENDS_${PN} = "execline"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRCREV = "3a13f4d31fc5e8c7173100b6be78d92105edff61"
SRC_URI = "git://github.com/jjk-jacky/anopa;protocol=git;branch=next"

S = "${WORKDIR}/git"

do_configure() {
    ${S}/tools/gen-deps.sh > ${S}/package/deps.mak
	${S}/configure --enable-shared --enable-static --with-sysdeps=${STAGING_DIR_TARGET}/${libdir}/skalibs/sysdeps
    touch ${S}/-lskarnet
    touch ${S}/-ls6
    touch ${S}/-lexecline
}

do_install () {
    oe_runmake install DESTDIR=${D}
    # Not using stage0 or stage 4 right now, and it uses /bin/execlineb instead of
    # /usr/bin/execlineb, which causes bitbake to complain
    rm ${D}/usr/lib/anopa/aa-stage0
    rm ${D}/usr/lib/anopa/aa-stage4
}
