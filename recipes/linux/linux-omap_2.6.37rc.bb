require multi-kernel.inc

DESCRIPTION = "Linux kernel for OMAP processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "beagleboard"

# The main PR is now using MACHINE_KERNEL_PR, for omap3 see conf/machine/include/omap3.inc
PV = "2.6.36+2.6.37-rc3"
MACHINE_KERNEL_PR_append = "b+gitr${SRCREV}"
SRCREV = "063d907edaf3fbf9776c189c3e02f2c7a129d18c"

FILESPATHPKG_prepend = "linux-omap-2.6.37rc:"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/tmlind/linux-omap-2.6.git;protocol=git \
	   file://defconfig"

SRC_URI_append = " \
                  file://0001-ARM-OMAP-beagleboard-Add-infrastructure-to-do-fixups.patch \
                  file://0002-modedb.c-add-proper-720p60-mode.patch \
                  file://0003-ARM-OMAP-fix-USB-initialization-for-beagleboard-xM.patch \
                  file://0004-ARM-OMAP-Power-on-EHCI-serial-camera-and-DVI-on-beag.patch \
                  file://0005-mmc-don-t-display-single-block-read-console-messages.patch \
                  file://0006-MTD-silence-ecc-errors-on-mtdblock0.patch \
                  file://0007-Miracle-patch.patch \
                  file://0008-ARM-OMAP-add-omap_rev_-macros.patch \
"

SRC_URI_append_beagleboard = " file://logo_linux_clut224.ppm \
"

S = "${WORKDIR}/git"

