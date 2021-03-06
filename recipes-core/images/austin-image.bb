DESCRIPTION = "the image that supports the Austin board."

PR = "r01"

IMAGE_INSTALL = "packagegroup-core-boot ${ROOTFS_PKGMANAGE_BOOTSTRAP} ${CORE_IMAGE_EXTRA_INSTALL}"
IMAGE_LINGUAS = " "

LICENSE = "Ohsung"

inherit core-image

# for development 
DEVEL_INSTALL = " \ 
				dropbear \ 
				lrzsz \ 
				mtd-utils \ 
				wireless-tools \
				imx-test \
				dropbear \
				lrzsz \
				kobs-ng \
				"

IMAGE_INSTALL += " \
				psplash \
				udev \ 
				${DEVEL_INSTALL} \
				"

IMAGE_DEVICE_TABLES = "files/device_table-austin.txt"

UBI_VOLNAME = "rootfs"
IMAGE_ROOTFS_SIZE = "8192"

# remove not needed ipkg informations
ROOTFS_POSTPROCESS_COMMAND += "remove_packaging_data_files ; "

