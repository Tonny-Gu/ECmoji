#!/bin/bash
passwd=$(cat ../secret/archive_passwd.txt)
if [ -z "$passwd"]; then
    echo "Please input the password of the archive file"
    read passwd
fi
unzip -r secret.zip ../secret -P $passwd 