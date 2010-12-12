DESCRIPTION = "PRU sw example applications"
HOMEPAGE = "https://gforge.ti.com/gf/project/pru_sw/"
LICENSE = "BSD"
DEPENDS = "ti-pru-sw-app-loader ti-pru-sw-edma-library"
PR = "r0+svnr${SRCPV}"

COMPATIBLE_MACHINE = "omapl138|am180x-evm"

SRC_URI = "svn://gforge.ti.com/svn/pru_sw/;module=trunk;proto=https;user=anonymous;pswd=''"

SRCREV = "18"
S = "${WORKDIR}/trunk"

do_compile () {
        make -C ${S}/example_apps LIBDIR_APP_LOADER="${D}${libdir}" \
          CC=${TOOLCHAIN_PATH}/bin/${TARGET_PREFIX}gcc \
          LIBDIR_EDMA_LOADER="${D}${libdir}}" \
          INCDIR_APP_LOADER="${D}${includedir}" \
          INCDIR_EDMA_DRIVER="${D}${includedir}" \
          BINDIR_APPLICATIONS="${S}/example_apps/bin" \
          BINDIR_FW="${S}/example_apps/bin" \
          UTILS_DIR="${S}/utils"
}

do_install () {
        install -d ${D}/usr/share/ti/ti-pru-eg/
        install -m 0755 ${S}/example_apps/bin/* ${D}/usr/share/ti/ti-pru-eg/
}

FILES_${PN} += "${datadir}/ti/ti-pru-eg/*"
FILES_${PN}-dbg += "${datadir}/ti/ti-pru-eg/.debug/*"
