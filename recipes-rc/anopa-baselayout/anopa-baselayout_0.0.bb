inherit allarch
inherit deploy

DESCRIPTION = "Init base for S6/anopa"
HOMEPAGE = ""
LICENSE = "MIT"
SECTION = "base"
RDEPENDS_${PN} = "execline"
LIC_FILES_CHKSUM = "file://../COPYING;md5=22aae3ee3a239ced3a3ebdba09260941"
SRC_URI = "file://init \
           file://getty@-run \
           file://uncaught-logs-run \
           file://PATH \
           file://COPYING \
          "

#do_fetch[noexec] = "1"
#do_unpack[noexec] = "1"
do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

inherit useradd
USERADD_PACKAGES = "${PN}"

USERADD_PARAM_${PN} = "-u 0 -d /root -r -s /bin/sh root; -u 65534 -r -s /bin/false nobody"
GROUPADD_PARAM_${PN} = "-g 0 root; -g 65534 nogroup"

do_install () {
    #install -d ${D}${sysconfdir}
    install -d ${D}/sbin
    install -m 0744 ${B}/../init ${D}/sbin/
    install -d ${D}/etc/anopa/env
    install -m 0444 ${B}/../PATH ${D}/etc/anopa/env
    install -d ${D}/etc/anopa/enabled
    touch ${D}/etc/anopa/enabled/uncaught-logs
    touch ${D}/etc/anopa/enabled/getty@tty1
    touch ${D}/etc/anopa/enabled/getty@ttyS0
    install -d ${D}/etc/anopa/services
    install -d ${D}/etc/anopa/services/uncaught-logs
    # If we make the fifo here, yocto QA hangs trying to read from it :-(
    # We'll make the fifo in our /sbin/init
    #mkfifo ${D}/etc/anopa/services/uncaught-logs/fifo
    install -m 0744 ${B}/../uncaught-logs-run ${D}/etc/anopa/services/uncaught-logs/run
    install -d ${D}/etc/anopa/services/getty@
    install -m 0744 ${B}/../getty@-run ${D}/etc/anopa/services/getty@/run
    install -d ${D}/etc/anopa/listdirs
    install -d ${D}/etc/anopa/listdirs/onboot
    touch ${D}/etc/anopa/listdirs/onboot/getty@tty1
    touch ${D}/etc/anopa/listdirs/onboot/getty@ttyS0
    install -d ${D}/var
    # NOTE: via fs-perms.txt, yocto typically forces /var/log to be a symlink
    # to /var/volatile/log, but this is a matter of builder's policy. We'll
    # make sure the directories exist if no policy is set. Subdirs in log must
    # be handled by the individual logger's scripts and not us here. See the
    # run script for uncaught-logs for an example.
    install -d ${D}/var/log
    install -d ${D}/var/volatile
    install -d ${D}/proc
    install -d ${D}/sys
    install -d ${D}/dev
}

#FILES_${PN}="/var/log /var/log/boot /var/log/init.d /var/log/blah /"
FILES_${PN}="/"
