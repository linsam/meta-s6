require s6.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=d7f013666c13fc6c8d0127687dbfa63b"

SRC_URI[md5sum] = "135b7854e821c58097761e48320a340e"
SRC_URI[sha256sum] = "146dd54086063c6ffb6f554c3e92b8b12a24165fdfab24839de811f79dcf9a40"

# s6 2.4.0.0 needs skalibs >= 2.4.0.0  execline >= 2.2.0.0
# s6 2.6.0.0 needs skalibs >= 2.5.1.1  execline >= 2.3.0.1
RDEPENDS_${PN} = "skalibs (>= 2.5.1.1)"
