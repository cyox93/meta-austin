AUSTIN_MEM_TYPE = "MEM_MDDR"
EXTRA_OEMAKE += "MEM_TYPE=${AUSTIN_MEM_TYPE}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://austin-fix-images-paths.patch \
			"

CMDL_CONSOLE = "console=ttyAMA0,115200 kgdboc=ttyAMA0,115200"
CMDL_LCD = "lcd_panel=lb02001"
CMDL_IP = "ip=192.168.0.20"

CMDL_INTRAMFS = "rdinit=/bin/sh"
CMDL_UBIFS = "noinitrd ubi.mtd=1 rootfstype=ubifs root=ubi0 rw ssp1=mmc gpmi"
CMDL_NFS = "${CMDL_IP} root=/dev/nfs rw nfsroot=192.168.0.10:/project/tftpboot/rootfs"

export CMDLINE1="${CMDL_CONSOLE} ${CMDL_LCD} ${CMDL_UBIFS}"
export CMDLINE2="${CMDL_CONSOLE} ${CMDL_LCD} ${CMDL_NFS}"
export CMDLINE3="${CMDL_CONSOLE} ${CMDL_LCD} ${CMDL_INTRAMFS}"
export CMDLINE4="${CMDL_CONSOLE} ${CMDL_LCD} ${CMDL_NFS}"


do_configure_append () {
    # Use machine specific binaries
    sed 's,@MACHINE@,${MACHINE},g;s,@DTB@,-dtb,g' < updater.bd > updater.bd-dtb
    sed -i 's,@MACHINE@,${MACHINE},g;s,@DTB@,,g' updater.bd
}

do_deploy_append() {
    install -d ${DEPLOY_DIR_IMAGE}

	# install updater.bd files
	for f in updater.bd updater.bd-dtb; do
        full_name="imx-bootlets-`basename $f`-${MACHINE}-${PV}-${PR}"
        symlink_name="imx-bootlets-`basename $f`-${MACHINE}"

        install -m 644 ${S}/$f ${DEPLOY_DIR_IMAGE}/$full_name
        (cd ${DEPLOY_DIR_IMAGE} ; rm -f $symlink_nake ; ln -sf $full_name $symlink_name)
    done
}

