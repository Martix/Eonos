require linux.inc

PR = "r4"

module_autoload_ohci-hcd_omap5912osk = "ohci-hcd"

# Mark archs/machines that this kernel supports
DEFAULT_PREFERENCE = "-1"
DEFAULT_PREFERENCE_qemuarm = "1"
DEFAULT_PREFERENCE_qemuppc = "1"
DEFAULT_PREFERENCE_qemush4 = "1"
DEFAULT_PREFERENCE_qemumips = "1"
DEFAULT_PREFERENCE_qemumipsel = "1"
DEFAULT_PREFERENCE_qemux86 = "1"
DEFAULT_PREFERENCE_lite5200 = "1"
DEFAULT_PREFERENCE_omap5912osk = "1"
DEFAULT_PREFERENCE_tqm8540 = "1"
DEFAULT_PREFERENCE_ts72xx = "1"
DEFAULT_PREFERENCE_om-gta01 = "1"
DEFAULT_PREFERENCE_om-gta02 = "1"

SRC_URI = "${KERNELORG_MIRROR}/pub/linux/kernel/v2.6/linux-${PV}.tar.bz2;name=kernel \
           ${KERNELORG_MIRROR}/pub/linux/kernel/v2.6/patch-${PV}.7.bz2;apply=yes;name=stablepatch \
           file://ARM-Add-support-for-LZMA-compressed-kernel-images.patch;status=pending \
           file://defconfig"

SRC_URI_append_c7x0 = " file://fix-corgi-card-detection.patch;status=pending "

SRC_URI_append_ts72xx = " \
           file://0001-ts72xx_base.patch \
           file://0002-ts72xx_force_machine-id.patch \
           file://0003-ep93xx_cpuinfo.patch \
           file://0004-ep93xx_eth.patch \
           file://0005-ep93xx-m2m-DMA-support.patch \
           file://0006-ts72xx_rs485.patch \
           file://0007-ts72xx_ts_ser1.patch \
           file://0008-ts72xx_ts_eth100.patch \
           file://0009-ts72xx_pata.patch \
           file://0010-ts72xx_gpio_i2c.patch \
           file://0011-ts72xx_dio_keypad.patch \
           file://0012-ts72xx_sbcinfo.patch \
           file://0013-ts72xx_max197.patch \
           file://0014-ts7200_nor_flash.patch \
           file://0015-ts72xx_sdcard.patch \
           file://0016-ts72xx_nand_flash.patch \
           file://0017-ep93xx_spi.patch \
           file://0018-ts72xx_spi_tmp124.patch \
           file://0019-watchdog-ts72xx_wdt-disable-watchdog-at-probe.patch \
           "

SRC_URI_append_om-gta01 = " \
           file://openmoko.patch \
           file://shr.patch \
           "

SRC_URI_append_om-gta02 = " \
           file://openmoko.patch \
           file://shr.patch \
           "

SRC_URI[kernel.md5sum] = "10eebcb0178fb4540e2165bfd7efc7ad"
SRC_URI[kernel.sha256sum] = "fa395fec7de633df1cb85b6248b8f35af98380ed128a8bc465fb48bc4d252633"
SRC_URI[stablepatch.md5sum] = "a88e4b5a9fcb23c2229301ac4dae1f1a"
SRC_URI[stablepatch.sha256sum] = "b146904ea07cc87a3fbcbd4eab51d331d56718431539e6aa29c24b072e6b7832"
