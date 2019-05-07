package proj.ecmoji.ml;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;

import proj.ecmoji.data.Comment;

public class Deepmoji {
    private static String serverName;
    private static int serverPort;
    private static Socket client;
    private static void initialize() throws Exception {
        try(Scanner scanner = new Scanner(new File("server/server_name.txt"))) {
            serverName = scanner.next();
        } catch(Exception e) {throw(e);}

        try(Scanner scanner = new Scanner(new File("server/server_socket_port.txt"))) {
            serverPort = Integer.valueOf(scanner.next());
        } catch(Exception e) {throw(e);}
    }
    private static String callServer(String message) throws Exception{
        client = new Socket(serverName, serverPort);
        OutputStream out = client.getOutputStream();
        //ObjectOutputStream sent = new ObjectOutputStream(out);
        PrintWriter sent = new PrintWriter(out);
        sent.print(message);
        sent.flush();

        InputStream in = client.getInputStream();
        Scanner receive = new Scanner(in);
        String rel = receive.next();
        
        client.close();
        return rel;
    }
    public static String scoreComments(ArrayList<Comment> comments) throws Exception{
        //return JSON.toJSONString(comments);
        String JSONstring = JSON.toJSONString(comments);
        System.out.println(JSONstring);
        return callServer(JSONstring);
        
    }
    public static void main(String[] args) throws Exception{
        initialize();
        ArrayList<Comment> list = new ArrayList<>();
        list.add(new Comment("happy"));
        list.add(new Comment("puppy"));

        System.out.println(scoreComments(list));
    }
}