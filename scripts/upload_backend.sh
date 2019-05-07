#!/bin/bash

addr=$(cat ../secret/server_address.txt)
port=$(cat ../secret/server_ssh_port.txt)

remote_exec()
{
    echo "ssh $addr -p $port $1"
    ssh $addr -p $port $1
}

scp -P $port -r ../backend $addr:~/DeepMoji/
scp -P $port -r ../secret $addr:~/DeepMoji/
scp -P $port -r ../config $addr:~/DeepMoji/
remote_exec "ps -ef | grep python | awk '{print \$2}' | xargs kill -9"
sleep 2
remote_exec "ps -ef | grep python | awk '{print \$2}' | xargs kill -9"
#remote_exec "bash ~/DeepMoji/backend/entrypoint.sh"
