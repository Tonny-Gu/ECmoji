# -*- coding: utf-8 -*-

import time
import numpy
import score_texts_emojis as deepmoji
import SocketServer
import json

class MyJSONEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, numpy.integer):
            return int(obj)
        elif isinstance(obj, numpy.floating):
            return float(obj)
        elif isinstance(obj, numpy.ndarray):
            return obj.tolist()
        else:
            return super(MyJSONEncoder, self).default(obj)

class MyTCPHandler(SocketServer.BaseRequestHandler):
    def handle(self):
        print(self.client_address)
        data = self.request.recv(4096).strip()
        print(data)

        result = scoreTexts(json.loads(data))
        result = json.dumps(result, cls=MyJSONEncoder)
        print(result)

        self.request.sendall(result)

listenAddress = ("0.0.0.0", 1101)

def initialize():
    try:
        cfgFile = open("secret/server_socket_port.txt")
        listenAddress = ("0.0.0.0", int(cfgFile.read()))
    finally:
        if cfgFile: cfgFile.close()

def scoreTexts(comments):
    TEST_SENTENCES = []
    for comment in comments:
        TEST_SENTENCES.append(comment["text"])
    
    result = deepmoji.scoreTexts(TEST_SENTENCES)
    return result

if __name__ == "__main__":
    initialize()
    server = SocketServer.ThreadingTCPServer(listenAddress, MyTCPHandler)
    server.serve_forever()

