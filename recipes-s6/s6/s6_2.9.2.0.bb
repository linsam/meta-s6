require s6.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=2875ff3bd035b06bc171bb0e55a5f228"

SRC_URI[md5sum] = "cb6e5b4db41e1a1c5b69c5efb3fbc44d"
SRC_URI[sha256sum] = "363db72af8fffba764b775c872b0749d052805b893b07888168f59a841e9dddd"

# s6 2.4.0.0 needs skalibs >= 2.4.0.0  execline >= 2.2.0.0
# s6 2.6.0.0 needs skalibs >= 2.5.1.1  execline >= 2.3.0.1
# s6 2.6.2.0 needs skalibs >= 2.6.2.0  execline >= 2.3.0.4
# s6 2.7.0.0 needs skalibs >= 2.6.3.0  execline >= 2.3.0.4
# s6 2.7.2.2 needs skalibs >= 2.7.0.0  execline >= 2.5.0.1
# s6 2.9.2.0 needs skalibs >= 2.9.2.1  execline >= 2.6.0.1
