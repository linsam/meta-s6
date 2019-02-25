require s6-dns.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=682e28b5c36e2b48d5b0cf2fbe8990f5"

SRC_URI[md5sum] = "9600a6da237939fbae82883c6952ce3a"
SRC_URI[sha256sum] = "0cc1c726347674fec2fd63acd135996d094ebbd6b1f2ab26607c754f1855d59b"

# 2.1.0.0 needs skalibs >= 2.4.0.0
# 2.2.0.0 needs skalibs >= 2.5.0.0
# 2.3.0.0 needs skalibs == 2.6.3.0
# 2.3.0.1 needs skalibs >= 2.7.0.0
