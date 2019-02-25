require s6.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=682e28b5c36e2b48d5b0cf2fbe8990f5"

SRC_URI[md5sum] = "49d7d214c41cc49b839834a518f21ca5"
SRC_URI[sha256sum] = "58dfa7633caff3790e06b5981344ceb03a1cedff4f253e15d79135d9a2ab525f"

# s6 2.4.0.0 needs skalibs >= 2.4.0.0  execline >= 2.2.0.0
# s6 2.6.0.0 needs skalibs >= 2.5.1.1  execline >= 2.3.0.1
# s6 2.6.2.0 needs skalibs >= 2.6.2.0  execline >= 2.3.0.4
# s6 2.7.0.0 needs skalibs >= 2.6.3.0  execline >= 2.3.0.4
# s6 2.7.2.2 needs skalibs >= 2.7.0.0  execline >= 2.5.0.1
