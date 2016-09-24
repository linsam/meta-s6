inherit allarch
inherit deploy

DESCRIPTION = "Init base for S6+anopa"
HOMEPAGE = ""
LICENSE = "MIT"
SECTION = "base"
LIC_FILES_CHKSUM = "file://../COPYING;md5="

RDEPENDS_anopa-image-initrd="anopa-initrd"

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
for i in \`/bin/busybox seq 30\`; do
  if [ -e /dev/hda ]; then
    break;
  fi
  if [ -e /dev/hdc ]; then
    break;
  fi
  aa-echo -D "Waiting for disk \$i / 30"
  sleep 1
done
if [ \$i -eq 30 ]; then
  aa-echo -De disk not found
  exit 1
fi
if [ -e /dev/hda ]; then
    # We are on disk image
    mount /dev/hda /tmpmnt
else
    # We are on cd/iso image
    mount /dev/hdc /tmpmnt
fi
cp /tmpmnt/rootfs.img /
umount /tmpmnt
mount -oloop /rootfs.img /root-fs
[ -e /root-fs/run ] || mkdir /root-fs/run
[ -e /root-fs/proc ] || mkdir /root-fs/proc
mount --bind /proc /root-fs/proc
mount --bind /dev /root-fs/dev
EOF
    cat >${D}/services/rootfs/stop <<EOF
#!/bin/sh
umount /root-fs/proc
umount /root-fs/sys
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

PACKAGES="anopa-image-initrd"
FILES_anopa-image-initrd="/init /shutdown /etc/anopa /services /root-fs"

