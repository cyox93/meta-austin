DESCRIPTION = "kobs-ng util"
LICENSE = "Ohsung"
LIC_FILES_CHKSUM = "file://kobs-ng;md5=9b103648af2e459e3e9ed7d8677372b4"
PROJECTDIR = "imx23austin"

PR = "r00"

SRC_URI = "file://kobs-ng  \
			"

INSANE_SKIP_${PN} = "ldflags" 
INSANE_SKIP_${PN}-dev = "ldflags" 

do_configure_prepend() {
	install -m 0755 ${WORKDIR}/kobs-ng ${S}/kobs-ng
}

do_install_append() {
	install -d ${D}/usr/bin
	install -m 0755 ${WORKDIR}/kobs-ng ${D}/usr/bin/kobs-ng
}

