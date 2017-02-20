require s6.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6ff3b66afd85801a2be9d46214f4e7b5"

SRC_URI[md5sum] = "e5c01be33a0cb6cbc76bd4382f94452f"
SRC_URI[sha256sum] = "49c9a0476d93c6279c776ca5ae9c49b3fd39c356eda674828aebf78f8488e8e6"

SRC_URI += " file://configure.patch"
