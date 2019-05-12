package proj.ecmoji.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import proj.ecmoji.data.StorageServer;

public class EmojiPrinter {
    final private static String emojiJSONpath = "resource/emoji.json";
    final private static Map<String, String> emoji = new HashMap<>();
    final private static Gson gson = new Gson();
    static {
        try {
            Map<String, String> JSONcontent = gson.fromJson(TXTtokenLoader.readLine(emojiJSONpath), new TypeToken<Map<String, String>>(){}.getType());
            if(JSONcontent!=null) emoji.putAll(JSONcontent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String getEmoji(String emojiID) {
        return emoji.get(emojiID);
    }
    public static void main(String[] args) throws Exception {
        StorageServer.load();
        StorageServer.getProductList().forEach(e->{
            System.out.printf("####Product Name: %s#####\n", e.getName());
            e.getComments().forEach(c->{
                System.out.println(c);
                c.getScore().forEach((k, v)->{
                    System.out.printf("%s: ", getEmoji(k));
                    int i;
                    for(i=0; i<(int)(v*50); ++i) System.out.print("#");
                    for(; i<50; ++i) System.out.print(".");
                    System.out.println();
                });
                System.out.println();
            });
            System.out.println("\n");
        });
    }
}