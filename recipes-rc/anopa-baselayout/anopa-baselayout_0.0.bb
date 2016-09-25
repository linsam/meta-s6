inherit allarch
inherit deploy

DESCRIPTION = "Init base for S6/anopa"
HOMEPAGE = ""
LICENSE = "MIT"
SECTION = "base"
DEPENDS = "s6"
LIC_FILES_CHKSUM = "file://../COPYING;md5="
SRC_URI = "file://init \
           file://PATH \
           file://COPYING \
          "

#do_fetch[noexec] = "1"
#do_unpack[noexec] = "1"
do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

#do_install () {
#    oe_runmake install DESTDIR=${D}
#    install 
#}

do_install () {
    sleep 2
    #install -d ${D}${sysconfdir}
    install -d ${D}/sbin
    install -m 0744 ${B}/../init ${D}/sbin/
    install -d ${D}/etc/anopa/env
    install -m 0444 ${B}/../PATH ${D}/etc/anopa/env
    install -d ${D}/var/volatile
    install -d ${D}/var/run
    install -d ${D}/proc
    install -d ${D}/sys
    install -d ${D}/dev
    install -d ${D}/var/log/boot
    install -d ${D}/var/log/init.d
}

FILES_${PN}="/"
