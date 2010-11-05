# this script can be used for testing purposes.
# see also http://wiki.openembedded.net/index.php/TestingScript

# you can define your machine/distro/recipe below (remove the #) 
# or you can pick them up from the environment
#MACHINE="beagleboard"
#DISTRO="minimal"
#TARGET_RECIPE="console-image"

# test if we have an openembedded dir, clone it if it does not exist
if [ ! -d openembedded ]
then
    (git clone git://git.openembedded.org/openembedded)
    (cd openembedded; git checkout -b testing-next origin/testing-next)
fi

# switch to the testing branch
(cd openembedded; git checkout testing-next)

# test if bitbake exist; if not; fetch it and untar it
if [ ! -d bitbake-1.10.1 ]
then
    (wget http://download.berlios.de/bitbake/bitbake-1.10.1.tar.gz; tar xf bitbake-1.10.1.tar.gz; rm bitbake-1.10.0.tar.gz) 
fi

# TOPDIR is where we are now
TOPDIR=`pwd`

# add bitbake to the path
export PATH=${TOPDIR}/bitbake-1.10.1/bin:$PATH

# create a local.conf by using a here document
cat > ${TOPDIR}/openembedded/conf/local.conf << EOF
# fix next line if you want to use your own mirror, then remove the # for the next two lines
# You can start a trivial server with 'python -m SimpleHTTPServer'
#SOURCE_MIRROR_URL = "http://localhost:8000/directory"
#INHERIT += "own-mirrors"

DL_DIR = "${TOPDIR}/downloads"

# if you want to keep tmp dirs for different builds you might want to set TMPDIR to e.g. ${TOPDIR}/tmp_${MACHINE}_${DISTRO}
TMPDIR = "${TOPDIR}/tmp"
BBFILES = "${TOPDIR}/openembedded/recipes/*/*.bb"
ENABLE_BINARY_LOCALE_GENERATION = "0"

# Which files do we want to parse:
BBMASK = ""

# ccache always overfill $HOME....
CCACHE=""

# What kind of images do we want?
IMAGE_FSTYPES = "tar.gz "

# Make use of my SMP box
PARALLEL_MAKE="-j4"
BB_NUMBER_THREADS = "2"

OE_STRICT_CHECKSUMS = "1"

# if you are low on disk space you can remove the next #, disadvantage, nastier debugging in case of failures
#INHERIT += "rm_work"

# if you want to report build results (recommended) you need to edit OESTATS_BUILDER and add your name or nick in it, then uncomment the next 3 lines.
#INHERIT += "oestats-client"
#OESTATS_SERVER = "tinderbox.openembedded.net"
#OESTATS_BUILDER = "your nick goes here"

EOF

# smake sure BB_ENV_EXTRAWHIT is correct, and export the needed vars
BB_ENV_EXTRAWHITE="MACHINE DISTRO TOPDIR"
export BB_ENV_EXTRAWHITE MACHINE DISTRO TOPDIR
export BBPATH=${TOPDIR}/openembedded

# pull the current branch; in case a stale lock exists remove it
(cd openembedded; rm -f .git/index.lock;git pull)

# clean tmp; I want to start with a clean build; if you changed TMPDIR in the conf file better change it here too.
rm -rf ${TOPDIR}/tmp

# add an echo about the vars so we can see what has been done in a log file
echo $MACHINE $DISTRO $TARGET_RECIPE

# and do the actual work.
bitbake ${TARGET_RECIPE}

