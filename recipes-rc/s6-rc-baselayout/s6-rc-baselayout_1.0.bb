inherit allarch
inherit deploy

DESCRIPTION = "Init base for S6/s6-rc"
HOMEPAGE = ""
LICENSE = "MIT"
SECTION = "base"
RDEPENDS_${PN} = "execline"
DEPENDS = "s6-rc-native s6-linux-init s6-linux-init-native"
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
    install -d ${D}/etc/s6-rc/source/default
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
    # /proc and /sys are now part of base-files (at least as of poky-sumo. It
    # wasn't by the end of poky-morty; not sure when between the two this
    # happened)
    #install -d ${D}/proc
    #install -d ${D}/sys
    install -d ${D}/dev
    install -m 0744 ${B}/../rc.init ${D}/etc/rc.init
    install -m 0744 ${B}/../rc.tini ${D}/etc/rc.tini
    install -m 0744 ${B}/../rc.shutdown ${D}/etc/rc.shutdown
    
    echo "bundle" >${D}/etc/s6-rc/source/default/type
    echo "ok-all" >${D}/etc/s6-rc/source/default/contents
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
    PSEUDO_DISABLED=1 s6-rc-compile ${D}/etc/s6-rc/compiled-initial ${D}/etc/s6-rc/source
    chown 0:0 -R ${D}/etc/s6-rc/compiled-initial
    ln -s compiled-initial ${D}/etc/s6-rc/compiled
    # Finally, the compiled tree has some intrinsic services
    # (s6rc-oneshot-runner, s6rc-fdholder) that get created with an
    # interpreter line calling out the execlineb that s6-rc-compile knows of,
    # which in our case is unfortunately the deep install path within the
    # yocto host sysroot. This of course won't work on our target system, so
    # fixup those lines
    CSVCDIR=${D}/etc/s6-rc/compiled-initial/servicedirs
    sed -i '1 c#!/bin/execlineb -P' ${CSVCDIR}/s6rc-fdholder/run
    sed -i '1 c#!/bin/execlineb -P' ${CSVCDIR}/s6rc-oneshot-runner/run
    # And we need to clean up other utilities
    # TODO: PSEUDO_PREFIX is probably the wrong thing to key off of.
    sed -i "s,/.*s6-rc-fdholder-filler,${prefix}/libexec/s6-rc-fdholder-filler," ${CSVCDIR}/s6rc-fdholder/run
    sed -i "s,/.*s6-rc-oneshot-run,${prefix}/libexec/s6-rc-oneshot-run," ${CSVCDIR}/s6rc-oneshot-runner/run

    install -d ${D}/sbin

    ##############################################
    # We'll now build up roughly what s6-linux-init-maker does by hand
    echo '#!/bin/execlineb -S0\n\ns6-linux-init -c "/etc/s6-linux-init/current" -m 0022 -p "/usr/bin:/sbin:/bin" -- "$0"' > ${D}/sbin/init
    chmod +x ${D}/sbin/init
    mkdir ${D}/etc/s6-linux-init
    mkdir ${D}/etc/s6-linux-init/current
    mkdir ${D}/etc/s6-linux-init/current/bin
    mkdir ${D}/etc/s6-linux-init/current/env
    mkdir ${D}/etc/s6-linux-init/current/run-image
    mkdir ${D}/etc/s6-linux-init/current/scripts
    echo '#!/bin/sh -e\n\nrl="$1"\nshift\ns6-rc-init /run/service\nexec /etc/s6-linux-init/current/scripts/runlevel "$rl"' > ${D}/etc/s6-linux-init/current/scripts/rc.init
    echo '#!/bin/sh -e\n\nexec s6-rc -v2 -bda change' >${D}/etc/s6-linux-init/current/scripts/rc.shutdown
    echo '#!/bin/sh -e\n\n# Things to do *right before* the machine gets rebooted or\n# powered off, at the very end of the shutdown sequence\n# when all the filesystems are unmounted' >${D}/etc/s6-linux-init/current/scripts/rc.shutdown.final
    echo '#!/bin/sh -e\n\ntest "$#" -gt 0 || { echo 'runlevel: fatal: too few arguments' 1>&2 ; exit 100 ; }\n\nexec s6-rc -v2 -up change "$1"' >${D}/etc/s6-linux-init/current/scripts/runlevel
    chmod +x ${D}/etc/s6-linux-init/current/scripts/*
    mkdir ${D}/etc/s6-linux-init/current/run-image/uncaught-logs
    mkdir ${D}/etc/s6-linux-init/current/run-image/service
    mkdir ${D}/etc/s6-linux-init/current/run-image/service/s6-linux-init-shutdownd
    #mkfifo ${D}/etc/s6-linux-init/current/run-image/service/s6-linux-init-shutdownd/fifo
    mknod ${D}/etc/s6-linux-init/current/run-image/service/s6-linux-init-shutdownd/fifo p
    echo '#!/bin/execlineb -P\n\ns6-linux-init-shutdownd -c "/etc/s6-linux-init/current" -g 3000' >${D}/etc/s6-linux-init/current/run-image/service/s6-linux-init-shutdownd/run
    chmod +x ${D}/etc/s6-linux-init/current/run-image/service/s6-linux-init-shutdownd/run
    mkdir ${D}/etc/s6-linux-init/current/run-image/service/.s6-svscan
    echo '#!/bin/execlineb -P\n\ns6-linux-init-shutdown -a -p -- now' >${D}/etc/s6-linux-init/current/run-image/service/.s6-svscan/SIGPWR
    echo '#!/bin/execlineb -P\n' >${D}/etc/s6-linux-init/current/run-image/service/.s6-svscan/SIGHUP
    echo '#!/bin/execlineb -P\n\ns6-linux-init-shutdown -a -h -- now' >${D}/etc/s6-linux-init/current/run-image/service/.s6-svscan/SIGUSR2
    echo '#!/bin/execlineb -P\n' >${D}/etc/s6-linux-init/current/run-image/service/.s6-svscan/SIGQUIT
    echo '#!/bin/execlineb -P\n\ns6-linux-init-shutdown -a -r -- now' >${D}/etc/s6-linux-init/current/run-image/service/.s6-svscan/SIGUSR2
    echo '#!/bin/execlineb -P\n\ns6-linux-init-shutdown -a -p -- now' >${D}/etc/s6-linux-init/current/run-image/service/.s6-svscan/SIGUSR1
    echo '#!/bin/execlineb -P\n' >${D}/etc/s6-linux-init/current/run-image/service/.s6-svscan/SIGWINCH
    echo '#!/bin/execlineb -P\n' >${D}/etc/s6-linux-init/current/run-image/service/.s6-svscan/SIGTERM
    echo '#!/bin/execlineb -P\n\nredirfd -w 2 /dev/console\nfdmove -c 1 2\nforeground { s6-linux-init-echo -- "s6-svscan exited. Rebooting." }\ns6-linux-init-hpr -fr' >${D}/etc/s6-linux-init/current/run-image/service/.s6-svscan/finish
    echo '#!/bin/execlineb -P\n\nredirfd -w 2 /dev/console\nfdmove -c 1 2\nforeground { s6-linux-init-echo -- "s6-svscan crashed. Rebooting." }\ns6-linux-init-hpr -fr' >${D}/etc/s6-linux-init/current/run-image/service/.s6-svscan/crash
    chmod +x ${D}/etc/s6-linux-init/current/run-image/service/.s6-svscan/*

    mkdir ${D}/etc/s6-linux-init/current/run-image/service/s6-svscan-log
    mknod ${D}/etc/s6-linux-init/current/run-image/service/s6-svscan-log/fifo p # would mkfifo instead, but tool is missing in recent poky
    echo 3 >${D}/etc/s6-linux-init/current/run-image/service/s6-svscan-log/notification-fd
    echo '#!/bin/execlineb -P\n\nfdmove -c 1 2\nredirfd -rnb 0 fifo\ns6-log -bpd3 -- 1 t /run/uncaught-logs' > ${D}/etc/s6-linux-init/current/run-image/service/s6-svscan-log/run
    chmod +x ${D}/etc/s6-linux-init/current/run-image/service/s6-svscan-log/run

    mkdir ${D}/etc/s6-linux-init/current/run-image/service/s6-linux-init-runleveld
    echo 3 >${D}/etc/s6-linux-init/current/run-image/service/s6-linux-init-runleveld/notification-fd
    echo '#!/bin/execlineb -P\n\nfdmove -c 2 1\nfdmove 1 3\ns6-ipcserver -1 -a 0700 -c 1 -- s\ns6-sudod -dt30000 --\n"/etc/s6-linux-init/current"/scripts/runlevel' > ${D}/etc/s6-linux-init/current/run-image/service/s6-linux-init-runleveld/run
    chmod +x ${D}/etc/s6-linux-init/current/run-image/service/s6-linux-init-runleveld/run


    mkdir ${D}/etc/s6-linux-init/current/run-image/service/s6-linux-init-early-getty
    echo '#!/bin/execlineb -P\n\n/sbin/getty 38400 tty2' > ${D}/etc/s6-linux-init/current/run-image/service/s6-linux-init-early-getty/run
    chmod +x ${D}/etc/s6-linux-init/current/run-image/service/s6-linux-init-early-getty/run

    echo '#!/bin/execlineb -S0\n\ns6-linux-init-hpr -h $@' > ${D}/etc/s6-linux-init/current/bin/halt
    echo '#!/bin/execlineb -S0\n\ns6-linux-init -c "/etc/s6-linux-init/current" -m 0022 -p "/usr/bin:/sbin:/bin" -- "$@"' > ${D}/etc/s6-linux-init/current/bin/init # Same as we did for /sbin/init; probably don't need it in both places, but whatever
    echo '#!/bin/execlineb -S0\n\ns6-linux-init-hpr -p $@' > ${D}/etc/s6-linux-init/current/bin/poweroff
    echo '#!/bin/execlineb -S0\n\ns6-linux-init-hpr -r $@' > ${D}/etc/s6-linux-init/current/bin/reboot
    echo '#!/bin/execlineb -S0\n\ns6-linux-init-telinit $@' > ${D}/etc/s6-linux-init/current/bin/telinit
    echo '#!/bin/execlineb -S0\n\ns6-linux-init-shutdown $@' > ${D}/etc/s6-linux-init/current/bin/shutdown
    chmod +x ${D}/etc/s6-linux-init/current/bin/*
    mkdir ${D}/bin
    cp ${D}/etc/s6-linux-init/current/bin/* ${D}/bin/
}

# Disable QA, can't handle fifo in image (opens and blocks). A patch was supposed to go out around 2016; not in sumo yet? https://lists.yoctoproject.org/g/yocto/topic/61310617
python do_package_qa() {
}

#FILES_${PN}="/var/log /var/log/boot /var/log/init.d /var/log/blah /"
FILES_${PN}="/"
