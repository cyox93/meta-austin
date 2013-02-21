# Copyright (C) 2011-2012 Freescale Semiconductor
# Released under the MIT license (see COPYING.MIT for the terms)

#INC_PR = "r53"

include linux-imx.inc

PR = "r53"
SRCREV = "a3e9a8317924467fa0c0bd50bd4274e379dc47a5"

GIT_USER_ID ?= "${@whoami()}"

SRC_URI = "git://${GIT_USER_ID}@192.168.150.109:29418/austin/kernel/linux-imx.git;protocol=ssh \
           file://${PROJECTDIR}/defconfig \
		   file://linux-imx-2.6.53_mtd_ubi_headers.patch \
"

