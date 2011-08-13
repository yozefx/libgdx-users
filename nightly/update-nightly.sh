#!/bin/sh

# script to update nightlies from:
# http://libgdx.l33tlabs.org/
# make sure to .gitignore nightly/ dir

set -x

YESTERDAY=`TZ=aaa24 date +%Y%m%d`

# STAMP=$(date -u +%Z%Y%m%d)
# STAMP="20110812"    # to hardwire cos of dateline
STAMP=$YESTERDAY

echo "getting nightly: $STAMP"

mkdir libs
rm -rf libs/*

curl http://libgdx.l33tlabs.org/libgdx-nightly-$STAMP.zip > libs/nightly.zip

cd libs
unzip nightly.zip
rm nightly.zip

cd ..

echo "copying..."

desktop="../demos"
android="../demos-android"

# shared libs
cp -r libs/* ../gdx-shared-libs/libs

# setup desktop
cp -r libs/* $desktop/libs
rm -rf $desktop/libs/docs

# setup android - fewer files needed
cp -r libs/armeabi $android/libs
cp -r libs/armeabi-v7a $android/libs
cp   libs/gdx.jar $android/libs
cp   libs/gdx-backend-android.jar $android/libs

