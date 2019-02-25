DESCRIPTION = "Anopa rc"
HOMEPAGE = "http://jjacky.com/anopa/"
LICENSE = "GPLv3+"
SECTION = "base"
DEPENDS = "skalibs execline s6"
RDEPENDS_${PN} = "execline anopa-common"
RDEPENDS_${PN}-initrd = "execline anopa-common"
RDEPENDS_${PN}-common = "skalibs execline s6"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
PR = "r2"

SRCREV = "191a888aff86bd61ea79cc72844d7ede63ce3735"
SRC_URI = "git://github.com/jjk-jacky/anopa;protocol=git;branch=next \
    "

S = "${WORKDIR}/git"

do_configure() {
    ${S}/tools/gen-deps.sh > ${S}/package/deps.mak
	${S}/configure --enable-shared --enable-static --with-sysdeps=${STAGING_DIR_TARGET}${libdir}/skalibs/sysdeps --with-lib=${STAGING_DIR_TARGET}${libdir} --with-dynlib=${STAGING_DIR_TARGET}${libdir} --exec-prefix=/
    touch ${S}/-lskarnet
    touch ${S}/-ls6
    touch ${S}/-lexecline
}

do_install () {
    oe_runmake install DESTDIR=${D}
}

PACKAGES+="${PN}-initrd ${PN}-common"

FILES_${PN} = "/libexec/aa-stage1 /libexec/aa-stage2 /libexec/aa-post-stage2 /libexec/aa-stage3"
FILES_${PN}-initrd = "/libexec/aa-stage0 /libexec/aa-post-stage0 /libexec/aa-stage4"
FILES_${PN}-common = "/lib /bin"
