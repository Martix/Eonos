require recipes/images/e-image-core.bb

DESCRIPTION = "Eonos - Enlightened open netbook operating system"

inherit image

PV = "0.0"
PR = "r0"

SPLASH = "exquisite exquisite-themes exquisite-theme-illume"

IMAGE_INSTALL += " \
  ${MACHINE_TASK_PROVIDER} \
  task-base-extended \
  initscripts sysvinit sysvinit-pidof \
#  ${SPLASH} \
  angstrom-x11-base-depends \
  gpe-login angstrom-gpe-task-base \
  e-wm-menu e-wm-config-netbook e-wm-config-illume2 \
  wireless-tools exalt exalt-client \
"

IMAGE_LINGUAS = ""
