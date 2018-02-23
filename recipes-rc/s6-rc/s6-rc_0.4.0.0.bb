require s6-rc.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=76d6194d4039114189edef052ff98418"

SRC_URI[md5sum] = "4b23b1b1ea5e924eb51f36efd19809da"
SRC_URI[sha256sum] = "96b8e7f275d42113b9ba7ef1d8856f64183b9421c39702f8332e6f5686496fba"

# s6-rc-0.1.0.0 requires 
#    skalibs >= 2.4.0.0,
#    execline >= 2.2.0.0,
#    s6 >= 2.4.0.0

# s6-rc-0.2.1.1 requires
#    skalibs >= 2.5.1.1
#    execline >= 2.3.0.1
#    s6 >= 2.6.0.0

# s6-rc-0.3.0.1 requires
#    skalibs >= 2.6.2.0
#    execline >= 2.3.0.4
#    s6 >= 2.6.2.0

# s6-rc-0.4.0.0 requires
#    skalibs >= 2.6.3.0
#    execline >= 2.3.0.4
#    s6 >= 2.6.2.0
