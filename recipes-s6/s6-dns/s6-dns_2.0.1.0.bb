require s6-dns.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6ff3b66afd85801a2be9d46214f4e7b5"

SRC_URI[md5sum] = "663d03eaeb74fd3abc95c704250e54f9"
SRC_URI[sha256sum] = "9c807f9202b498ea0d8132b287f126d72ef9a869bd0d736295a47f867c3c24ca"

SRC_URI += " file://configure.patch"
