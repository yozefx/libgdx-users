#!/bin/sh

cd ../bin
set -x

# send without delete
# new files will be sent to server
echo "sending"
rsync -avz -e ssh *apk dc@floyd.dreamhost.com:/home/dc/bitz.pikkle.com/apkz


