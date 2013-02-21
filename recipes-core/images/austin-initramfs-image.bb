# Initramfs image providing kexecboot
# a linux as bootloader implementation

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

# avoid circular dependencies
EXTRA_IMAGEDEPENDS = "virtual/kernel imx-bootlets"

DEVEL_INSTALL = " \
			klibc-utils-sh.shared \ 
			klibc-utils-halt \ 
			klibc-utils-ln \ 
			klibc-utils-ls \ 
			klibc-utils-mkdir \ 
			klibc-utils-mknod \ 
			klibc-utils-mv \ 
			klibc-utils-reboot \ 
			klibc-utils-sync \ 
			klibc-utils-umount \ 
			klibc-utils-cat \ 
			"

IMAGE_INSTALL = " \
			libklibc \
			klibc-utils-kinit.shared \
			klibc-utils-mount \
			${DEVEL_INSTALL} \
			"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

export IMAGE_BASENAME = "austin-initramfs-image"

IMAGE_LINGUAS = ""
IMAGE_LOGIN_MANAGER = ""
IMAGE_INIT_MANAGER = ""
IMAGE_INITSCRIPTS = ""
IMAGE_DEV_MANAGER = ""

FEED_DEPLOYDIR_BASE_URI = ""
ONLINE_PACKAGE_MANAGEMENT = "none"

inherit image

do_link() {
	cd ${IMAGE_ROOTFS}
	ln -sf bin/kinit.shared init

	cd ${IMAGE_ROOTFS}/bin
	ln -s sh.shared sh
}

#do_make_sb() {
# cd ${DEPLOY_DIR_IMAGE}
# elftosb -z -c imx-bootlets-linux_initramfs.bd-${MACHINE} -o ${MACHINE}.linux_initramfs.sb
#}

ROOTFS_POSTPROCESS_COMMAND += "remove_packaging_data_files ; "

IMAGE_PREPROCESS_COMMAND += "do_link"
#MACHINE_POSTPROCESS_COMMAND += "do_make_sb"
