SUMMARY = "Sysint application - Vendor"
SECTION = "console/utils"
LICENSE = "CLOSED"
 
SRC_URI = "${CMF_GITHUB_ROOT}/rdkvhal-sysint-raspberrypi4;${CMF_GIT_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"
 
do_compile[noexec] = "1"
 
do_install() {
        # RDKE-115: Dropbear drop-in conf for RPi
        install -d ${D}${systemd_unitdir}/system
        install -D -m 0644 ${S}/systemd_units/00-dropbear-vendor.conf ${D}${systemd_unitdir}/system/dropbear.service.d/00-dropbear.conf
        # Dropbear SSH banner
        install -d ${D}${sysconfdir}
        install -m 0644 ${S}/dropbear/sshbanner.txt ${D}${sysconfdir}
}
 
FILES_${PN} += "${systemd_unitdir}/system/dropbear.service.d/00-dropbear.conf"
FILES_${PN} += "${sysconfdir}/sshbanner.txt"
