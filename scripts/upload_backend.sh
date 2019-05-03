#!/bin/bash

addr="root@server8.sustc.dev3.cn"
port=1122

remote_exec()
{
    echo "ssh $addr -p $port $1"
    ssh $addr -p $port $1
}

scp -P $port -r ../backend $addr:~/DeepMoji/
scp -P $port -r ../config $addr:~/DeepMoji/
remote_exec "ps -ef | grep python | awk '{print \$2}' | xargs kill -9"
sleep 2
remote_exec "ps -ef | grep python | awk '{print \$2}' | xargs kill -9"
#remote_exec "bash ~/DeepMoji/backend/entrypoint.sh"
