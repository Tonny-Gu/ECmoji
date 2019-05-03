#!/bin/bash

__conda_setup="$('/root/anaconda2/bin/conda' 'shell.bash' 'hook' 2> /dev/null)"
if [ $? -eq 0 ]; then
    eval "$__conda_setup"
else
    if [ -f "/root/anaconda2/etc/profile.d/conda.sh" ]; then
        . "/root/anaconda2/etc/profile.d/conda.sh"
    else
        export PATH="/root/anaconda2/bin:$PATH"
    fi
fi
unset __conda_setup

conda activate deepmoji
cd ~/DeepMoji

python backend/server.py