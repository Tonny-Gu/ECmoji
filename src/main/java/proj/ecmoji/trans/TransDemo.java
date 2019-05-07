package proj.ecmoji.trans;

public class TransDemo {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "";
    private static final String SECURITY_KEY = "";

    public static void main(String[] args) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "手机出问题了用了一段时间失望";
        System.out.println(api.getTransResult(query, "auto", "en"));
    }

}
