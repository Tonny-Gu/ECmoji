package proj.ecmoji.ml;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;

import proj.ecmoji.util.TXTtokenLoader;

public class Deepmoji {
    private static String serverAddress;
    private static int serverPort;
    private static Socket client;
    static {
        try {
            initialize();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static void initialize() throws Exception {
        serverAddress = TXTtokenLoader.loadToken("secret/server_address.txt");
        serverPort = Integer.valueOf( TXTtokenLoader.loadToken("secret/server_socket_port.txt") );
    }
    private static String callServer(String message) throws Exception{
        client = new Socket(serverAddress, serverPort);
        OutputStream out = client.getOutputStream();
        PrintWriter sent = new PrintWriter(out);
        sent.print(message);
        sent.flush();

        InputStream in = client.getInputStream();
        Scanner receive = new Scanner(in);
        String rel = receive.next();
        
        receive.close();
        client.close();
        return rel;
    }
    public static List<DeepmojiScore> scoreSentences(List<String> sentences) throws Exception{
        String sentMessage = JSON.toJSONString(sentences);
        String recvMessage = callServer(sentMessage);
        return JSON.parseArray(recvMessage, DeepmojiScore.class);
    }
}
class DeepmojiScore {
    private final String text;
    private final Map<Integer, Double> score;
    public DeepmojiScore(String text, Map<Integer, Double> score) {
        this.text = text;
        this.score = new TreeMap<>();
        score.putAll(score);
    }
    public String getText() {return text;}
    public Map<Integer, Double> getScore() {return score;}
}