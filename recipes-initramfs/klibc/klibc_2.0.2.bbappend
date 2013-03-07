FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://remove_CAP_SYSLOG_for_kernel_2_6_35_austin.patch  \
			"

