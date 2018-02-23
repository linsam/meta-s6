require s6-linux-init.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=f92469ca440ddf0417773918c2dba935"

SRC_URI[md5sum] = "a3310a264f8162c5e108b86b30d55162"
SRC_URI[sha256sum] = "9ee2e8a5abc250bcb2be7d07566592ba5cbe3abce858f60853d3ac45b1ccdd79"

# 0.2.0.0 needs skalibs >= 2.4.0.0
# It builds a system needing (at boot):
#   execline >= 2.2.0.0
#   s6-portable-utils >= 2.1.0.0
#   s6-linux-utils >= 2.2.0.0
#   s6 >= 2.4.0.0

# 0.3.0.0 needs skalibs >= 2.5.0.0,
#    execline >= 2.3.0.0,
#    s6-portable-utils >= 2.2.0.0, 
#    s6-linux-utils >= 2.3.0.0,
#    s6 >= 2.5.0.0

# 0.3.1.1 needs skalibs >= 2.6.0.0,
#    execline >= 2.3.0.2,
#    s6-portable-utils >= 2.2.1.1, 
#    s6-linux-utils >= 2.4.0.0,
#    s6 >= 2.6.1.0
