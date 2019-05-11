package proj.ecmoji.ml;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import proj.ecmoji.data.DeepmojiScore;
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
        String rel = receive.nextLine();
        
        receive.close();
        client.close();
        return rel;
    }
    public static List<DeepmojiScore> scoreSentences(List<String> sentences) throws Exception{
        Gson gson = new Gson();
        String sentMessage = gson.toJson(sentences);
        String recvMessage = callServer(sentMessage);
        //System.out.println(recvMessage);
        return gson.fromJson(recvMessage, new TypeToken<List<DeepmojiScore>>(){}.getType());
    }
    public static DeepmojiScore scoreSentence(String sentence) throws Exception {
        List<String> sentences = new ArrayList<>();
        sentences.add(sentence);
        return scoreSentences(sentences).get(0);
    }
    public static void main(String[] args) throws Exception {
        System.out.println( scoreSentence( "Fuck you, asshole!" ) );
    }
}