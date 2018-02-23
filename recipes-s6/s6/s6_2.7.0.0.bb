require s6.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=682e28b5c36e2b48d5b0cf2fbe8990f5"

SRC_URI[md5sum] = "287d56d8cb5cc175ebe97fc1fcd91589"
SRC_URI[sha256sum] = "6617cbf82c73273c67c6102a1a5c48449ef65ffbe8d0db6a587b7f0078dc6e13"

# s6 2.4.0.0 needs skalibs >= 2.4.0.0  execline >= 2.2.0.0
# s6 2.6.0.0 needs skalibs >= 2.5.1.1  execline >= 2.3.0.1
# s6 2.6.2.0 needs skalibs >= 2.6.2.0  execline >= 2.3.0.4
# s6 2.7.0.0 needs skalibs >= 2.6.3.0  execline >= 2.3.0.4
