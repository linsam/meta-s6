inherit allarch
inherit deploy

DESCRIPTION = "Init base for S6/s6-rc"
HOMEPAGE = ""
LICENSE = "MIT"
SECTION = "base"
RDEPENDS_${PN} = "execline"
DEPENDS = "s6-rc-native s6-linux-init-native"
LIC_FILES_CHKSUM = "file://../COPYING;md5=22aae3ee3a239ced3a3ebdba09260941"
SRC_URI = "file://getty-s0-run \
           file://getty-tty1-run \
           file://00-up \
           file://mount-sys-up \
           file://mount-sys-down \
           file://mount-proc-up \
           file://mount-proc-down \
           file://rc.init \
           file://rc.tini \
           file://rc.shutdown \
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
    
    install -d ${D}/etc/s6-rc/source/00
    install -d ${D}/etc/s6-rc/source/getty-ttyS0
    install -d ${D}/etc/s6-rc/source/getty-tty1
    install -d ${D}/etc/s6-rc/source/getty-tty1
    install -d ${D}/etc/s6-rc/source/mount-sys
    install -d ${D}/etc/s6-rc/source/mount-proc
    install -d ${D}/etc/s6-rc/source/ok-local
    install -d ${D}/etc/s6-rc/source/ok-early
    install -d ${D}/etc/s6-rc/source/ok-all
    install -m 0744 ${B}/../getty-s0-run ${D}/etc/s6-rc/source/getty-ttyS0/run
    install -m 0744 ${B}/../getty-tty1-run ${D}/etc/s6-rc/source/getty-tty1/run
    install -m 0744 ${B}/../mount-proc-up ${D}/etc/s6-rc/source/mount-proc/up
    install -m 0744 ${B}/../mount-proc-down ${D}/etc/s6-rc/source/mount-proc/down
    install -m 0744 ${B}/../mount-sys-up ${D}/etc/s6-rc/source/mount-sys/up
    install -m 0744 ${B}/../mount-sys-down ${D}/etc/s6-rc/source/mount-sys/down
    install -m 0744 ${B}/../00-up ${D}/etc/s6-rc/source/00/up
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
    install -m 0744 ${B}/../rc.init ${D}/etc/rc.init
    install -m 0744 ${B}/../rc.tini ${D}/etc/rc.tini
    install -m 0744 ${B}/../rc.shutdown ${D}/etc/rc.shutdown
    echo "bundle" >${D}/etc/s6-rc/source/ok-all/type
    echo "ok-local" >${D}/etc/s6-rc/source/ok-all/contents
    echo "bundle" >${D}/etc/s6-rc/source/ok-local/type
    echo "bundle" >${D}/etc/s6-rc/source/ok-early/type
    echo "00\nmount-sys\nmount-proc" >${D}/etc/s6-rc/source/ok-early/contents
    echo "getty-ttyS0\ngetty-tty1" >${D}/etc/s6-rc/source/ok-local/contents
    echo "oneshot" >${D}/etc/s6-rc/source/00/type
    echo "oneshot" >${D}/etc/s6-rc/source/mount-sys/type
    echo "oneshot" >${D}/etc/s6-rc/source/mount-proc/type
    echo "longrun" >${D}/etc/s6-rc/source/getty-ttyS0/type
    echo "longrun" >${D}/etc/s6-rc/source/getty-tty1/type
    echo "ok-early" >${D}/etc/s6-rc/source/getty-ttyS0/dependencies
    echo "ok-early" >${D}/etc/s6-rc/source/getty-tty1/dependencies

    # The compile phase should really be part of making the image, after
    # everything is installed.
    # TODO: How to handle packages that want to add themselves to a bundle?
	# NOTE: Have to disable PSEUDO because s6-rc-compile does something PSEUDO
    # doesn't like. However, then we have to fix permissions
    PSEUDO_DISABLED=1 s6-rc-compile ${D}/etc/s6-rc/compiled ${D}/etc/s6-rc/source
    chown 0:0 -R ${D}/etc/s6-rc/compiled
    # Finally, the compiled tree has some intrinsic services
    # (s6rc-oneshot-runner, s6rc-fdholder) that get created with an
    # interpreter line calling out the execlineb that s6-rc-compile knows of,
    # which in our case is unfortunately the deep install path within the
    # yocto host sysroot. This of course won't work on our target system, so
    # fixup those lines
    CSVCDIR=${D}/etc/s6-rc/compiled/servicedirs
    sed -i '1 c#!/bin/execlineb -P' ${CSVCDIR}/s6rc-fdholder/run
    sed -i '1 c#!/bin/execlineb -P' ${CSVCDIR}/s6rc-oneshot-runner/run
    # And we need to clean up other utilities
    # TODO: PSEUDO_PREFIX is probably the wrong thing to key off of.
    sed -i "s,${PSEUDO_PREFIX},${prefix}," ${CSVCDIR}/s6rc-fdholder/run
    sed -i "s,${PSEUDO_PREFIX},${prefix}," ${CSVCDIR}/s6rc-oneshot-runner/run

    s6-linux-init-maker -s /run/bootenv ${D}/etc/s6-linux-init
    install -d ${D}/sbin
    ln -s /etc/s6-linux-init/init ${D}/sbin/init

}

#FILES_${PN}="/var/log /var/log/boot /var/log/init.d /var/log/blah /"
FILES_${PN}="/"
