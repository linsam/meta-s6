require s6-dns.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=2875ff3bd035b06bc171bb0e55a5f228"

SRC_URI[md5sum] = "a76e132a9f44b30a84c8b62c80a71feb"
SRC_URI[sha256sum] = "2ac75918ff5eb4d6dabe33f7e55fa70cf3e6a9062ff87de5a35029ea22238716"

# 2.1.0.0 needs skalibs >= 2.4.0.0
# 2.2.0.0 needs skalibs >= 2.5.0.0
# 2.3.0.0 needs skalibs == 2.6.3.0
# 2.3.0.1 needs skalibs >= 2.7.0.0
# 2.3.3.0 needs skalibs >= 2.9.3.0
