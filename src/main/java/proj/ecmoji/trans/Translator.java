package proj.ecmoji.trans;

import com.google.gson.JsonParser;

import proj.ecmoji.util.TXTtokenLoader;

public class Translator {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static String APP_ID;
    private static String SECURITY_KEY;
    static {
        try {
            initialize();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static void initialize() throws Exception {
        APP_ID = TXTtokenLoader.loadToken("secret/baidu_app_id.txt");
        SECURITY_KEY = TXTtokenLoader.loadToken("secret/baidu_security_key.txt");
    }
    public static String translateToEnglish(String plainText) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
        String result = api.getTransResult(plainText, "auto", "en");
        return new JsonParser().parse(result).getAsJsonObject()
            .get("trans_result").getAsJsonArray()
            .get(0).getAsJsonObject()
            .get("dst").getAsString();       
    }
    public static void main(String[] args) throws Exception {
        String query = "手机出问题了用了一段时间失望";
        System.out.println(Translator.translateToEnglish(query));
    }

}
