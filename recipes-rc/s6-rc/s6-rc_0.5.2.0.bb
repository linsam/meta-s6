require s6-rc.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=4116b5d84e17386dbe68724294cd8933"

SRC_URI[md5sum] = "62287a1adad875848c0d85861dc8139e"
SRC_URI[sha256sum] = "1ab21a9bdde61b50e3d9deab867e01f808064dce653b0ebf8e5f5125d57cfee2"

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

# s6-rc-0.5.2.0 requires
#   skalibs >= 2.9.3.0
#   execline >= 2.6.1.1
#   s6 >= 2.9.2.0
