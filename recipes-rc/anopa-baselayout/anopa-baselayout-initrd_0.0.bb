inherit allarch
inherit deploy

DESCRIPTION = "Init base for S6+anopa"
HOMEPAGE = ""
LICENSE = "MIT"
SECTION = "base"
LIC_FILES_CHKSUM = "file://../COPYING;md5="
PR='r1'

RDEPENDS_anopa-baselayout-initrd="anopa-initrd"

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
    #sleep 2
    #install -d ${D}${sysconfdir}
    ln -s /libexec/aa-stage0 ${D}/init
    ln -s /libexec/aa-stage4 ${D}/shutdown
    install -d ${D}/etc/anopa/env
    echo "/usr/sbin:/sbin:/usr/bin:/bin" > ${D}/etc/anopa/env/PATH
    install -d ${D}/services
    install -d ${D}/root-fs
    install -d ${D}/etc/anopa/listdirs/onboot
    install -d ${D}/etc/anopa/services/
    touch ${D}/etc/anopa/listdirs/onboot/rootfs
    install -d ${D}/services/rootfs
    cat >${D}/services/rootfs/start <<EOF
#!/bin/sh
# TODO: What should the default console level be?
dmesg -n 1
mkdir /proc
mount -t proc proc /proc
mount -t devtmpfs none /dev
mkdir /tmpmnt
aa-echo "Waiting for disk"
root=\`aa-incmdline root\`
timeout=15
for i in \`/bin/busybox seq \$timeout\`; do
  if [ -n "\$root" ]; then
    if [ -e "\$root" ]; then
      found="\$root"
      break;
    fi
  else
    # TODO: This fails if we boot from CD but the machine has an ide harddrive
    # installed (as hda)
    if [ -e /dev/hda ]; then
      found=/dev/hda
      break;
    fi
    if [ -e /dev/hdc ]; then
      found=/dev/hdc
      break;
    fi
  fi
  aa-echo -D "Waiting for disk \$i / 30"
  sleep 1
done
if [ \$i -eq \$timeout ]; then
  aa-echo -De disk not found
  exit 1
fi
mount \$found /tmpmnt || exit 1
if [ -e /tmpmnt/rootfs.img ]; then
    # This is a live CD/SD/whatever with real root in file
    cp /tmpmnt/rootfs.img / || exit 1
    umount /tmpmnt
    mount -oloop /rootfs.img /root-fs
else
    # This is a "regular" Linux system, not a live CD
    mount --move /tmpmnt /root-fs
fi
[ -e /root-fs/run ] || mkdir /root-fs/run
[ -e /root-fs/proc ] || mkdir /root-fs/proc
mount --bind /proc /root-fs/proc
mount --bind /dev /root-fs/dev
mount -ttmpfs tmpfs /root-fs/run
EOF
    cat >${D}/services/rootfs/stop <<EOF
#!/bin/sh
umount /root-fs/proc
umount /root-fs/sys
umount /root-fs/run
# The devtree of the main root tends to be in use until everything is gone.
# We'll move it into the initramfs so that we can finish cleaning up.
mkdir /old_dev
mount --move /root-fs/dev /old_dev
umount /root-fs/var/volatile
umount /root-fs
EOF
    chmod +x ${D}/services/rootfs/start
    chmod +x ${D}/services/rootfs/stop
    touch ${D}/services/rootfs/essential
}

PACKAGES="anopa-baselayout-initrd"
FILES_${PN}="/init /shutdown /etc/anopa /services /root-fs"

