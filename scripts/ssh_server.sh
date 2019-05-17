#!/bin/bash

addr=$(cat ../secret/server_ssh_address.txt)
port=$(cat ../secret/server_ssh_port.txt)

ssh -p $port $addr