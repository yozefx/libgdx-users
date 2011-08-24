#!/bin/sh

#author radioking and yatayata from libgdx-users project
# script to update nightlies from: http://libgdx.l33tlabs.org/
# make sure to .gitignore nightly/ dir

#set -x # Print command traces before executing command.
set +x # Stop printing command traces before executing command.

echo "creating temporary folder..."
TMPLIBDIR="libs_tmp"

mkdir $TMPLIBDIR
cd $TMPLIBDIR

NIGHTLIES="libgdx-nightly-latest.zip"
echo "getting nightly: $NIGHTLIES"
curl http://libgdx.l33tlabs.org/$NIGHTLIES > $NIGHTLIES

echo "extracting nightlies to temporary folder..."
unzip $NIGHTLIES

echo "copying libs to project 3-tier setup..."
DESKTOP="../../demos-3-tier-desktop/libs"
ANDROID="../../demos-3-tier-android/libs"
MAIN="../../demos-3-tier-main/libs"


# copy libs for android target
echo "...android target..."
cp -r armeabi                         $ANDROID
cp -r armeabi-v7a                     $ANDROID
cp    gdx-backend-android.jar         $ANDROID
cp    gdx-backend-android-sources.jar $ANDROID
# Not sure if this one is needed- read Android SDK
# having a bug exporting it from main project.
# bottomline was that it is needed?!
#cp    libs/gdx.jar                         $ANDROID/libs


# copy libs for desktop target
echo "...desktop target..."
cp gdx-backend-lwjgl-natives.jar $DESKTOP
cp gdx-backend-lwjgl.jar         $DESKTOP
cp gdx-natives.jar               $DESKTOP


# copy libs for main target
echo "...main target..."
cp gdx-sources.jar $MAIN
cp gdx.jar         $MAIN

echo "cleaning up..."
cd ..
rm -rf $TMPLIBDIR

echo "...all done. Don't forget to refresh all projects in Eclipse."
echo "Happy coding =)!"
