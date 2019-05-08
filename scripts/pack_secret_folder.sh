#!/bin/bash
passwd=$(cat ../secret/archive_passwd.txt)
if [ -z "$passwd"]
then
    echo "Please input the password of the archive file"
    read passwd
fi
if [ -d "../secret"]
then
    echo "secret.zip is removed"
    rm ../secret.zip
fi
cd .. && zip -r secret.zip ./secret -P $passwd
echo "secret.zip is created"