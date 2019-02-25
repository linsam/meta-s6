require s6-portable-utils.inc
LIC_FILES_CHKSUM = "file://COPYING;md5=682e28b5c36e2b48d5b0cf2fbe8990f5"

SRC_URI[md5sum] = "676fe5d9006a8fe16c48e91cd0b428ee"
SRC_URI[sha256sum] = "30a23761c77ba2a523100b4ab645796df4d5665f4ab4347d68cfbb7cc03fc745"

# s6-portable-utils 2.1.0.0 needs skalibs >= 2.4.0.0
# s6-portable-utils 2.2.1.0 needs skalibs >= 2.5.1.0
# s6-portable-utils 2.2.1.1 needs skalibs >= 2.6.0.0
# s6-portable-utils 2.2.1.2 needs skalibs >= 2.7.0.0
