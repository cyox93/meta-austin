FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://kernel_2_6_35_austin.patch  \
			file://kinit-customized-kinit-for-austin.patch  \
			"

