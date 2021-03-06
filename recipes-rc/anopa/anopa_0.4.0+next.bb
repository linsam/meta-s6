DESCRIPTION = "Anopa rc"
HOMEPAGE = "http://jjacky.com/anopa/"
LICENSE = "GPLv3+"
SECTION = "base"
DEPENDS = "skalibs execline s6"
RDEPENDS_${PN} = "execline anopa-common s6"
RDEPENDS_${PN}-initrd = "execline anopa-common"
RDEPENDS_${PN}-common = "execline"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRCREV = "c371ecc5a81140ce5b384a4fff936fd00d4079db"
SRC_URI = "git://github.com/jjk-jacky/anopa;protocol=git;branch=next \
    "

S = "${WORKDIR}/git"

# Attempt to add debug symbols; sometimes 'aa-status -aL' crashes with a
# malloc corruption that's hard to track down
EXTRA_OEMAKE = "CFLAGS=-g LDFLAGS=-g"

do_configure() {
    ${S}/tools/gen-deps.sh > ${S}/package/deps.mak
	${S}/configure --enable-shared --enable-static --with-sysdeps=${STAGING_DIR_TARGET}/${libdir}/skalibs/sysdeps
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
