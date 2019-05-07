#!/bin/bash
passwd=$(cat ../secret/archive_passwd.txt)
if [[ -z "$passwd"]]; then
    echo "Please input the password of the archive file"
    read passwd
fi
zip -r secret.zip ../secret -P $passwd 