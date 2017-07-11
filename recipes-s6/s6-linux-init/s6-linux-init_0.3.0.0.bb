require s6-linux-init.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=44134bb2d28ac2ef82e6501b78c10103"

SRC_URI[md5sum] = "881756a9470e26b7ee64c4fb63e85a42"
SRC_URI[sha256sum] = "9376f76ae3ab24cb18a35a6956d0fa5fe88cc4c1dfd19bbafc47dcd4f91c2535"

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
