require s6-linux-init.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=44134bb2d28ac2ef82e6501b78c10103"

SRC_URI[md5sum] = "24b9b232a5bcbb6be1c592ae1c23eb6d"
SRC_URI[sha256sum] = "131484e61eff0e671e112f0436b13e8e7d08752f810d8bec4924a68383eee2b9"

# 0.2.0.0 needs skalibs >= 2.4.0.0
# It builds a system needing (at boot):
#   execline >= 2.2.0.0
#   s6-portable-utils >= 2.1.0.0
#   s6-linux-utils >= 2.2.0.0
#   s6 >= 2.4.0.0

