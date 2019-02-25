require s6-rc.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=76d6194d4039114189edef052ff98418"

SRC_URI[md5sum] = "89cd111ab7820f0822a8f5dd07891a49"
SRC_URI[sha256sum] = "42b915d291946ad8f59a17f63b6bc95a94b4dea1c86e0659a9ec2650623f83f6"

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

# s6-rc-0.4.1.0 requires
#    skalibs >= 2.7.0.0
#    execline >= 2.5.0.1
#    s6 >= 2.7.2.0
