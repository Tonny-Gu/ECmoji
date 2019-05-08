#!/bin/bash
passwd=$(cat ../secret/archive_passwd.txt)
if [ -z "$passwd"]
then
    echo "Please input the password of the archive file"
    read passwd
fi
if [[ -d "../secret" && -f "../secret.zip" ]]
then
    echo "secret/ is removed"
    rm -r ../secret
fi
echo "secret/ is created"
cd .. && unzip -P $passwd -o secret.zip