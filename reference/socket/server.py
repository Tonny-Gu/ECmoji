import socket
import time

listenAddress = ("0.0.0.0", 1101)

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind(listenAddress)
server.listen(5)

while True:
    try:
        conn, addr = server.accept()
        data = conn.recv(1024)
        print("Received: "+data.decode())
        time.sleep(10)
        conn.send(data)
    except Exception as e:
        conn.close()
        print(e)
    conn.close()
