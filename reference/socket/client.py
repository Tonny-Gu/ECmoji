import socket

remoteAddress = ("server8.sustc.dev3.cn", 1101)
msg = "Hello world"

while True:
    client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client.connect(remoteAddress)
    client.send(msg.encode('utf-8'))
    data = client.recv(1024)
    print("Receive: "+data.decode())
    client.close()