package proj.ecmoji.web;

import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class FakeUA {
    private static Map<String, String> headers = new HashMap<>();
    static {
        headers.put("Accept", "*/*");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept-Language", "en-US, en; q=0.8, zh-Hans-CN; q=0.6, zh-Hans; q=0.4, ja; q=0.2");
        headers.put("Connection", "Keep-Alive");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36 Edge/18.18362");
        System.setProperty("http.agent", "");
    }

    public static URLConnection urlConnection(String url) throws Exception {
        URL u = new URL(url);
        URLConnection uc = u.openConnection();
        uc.setRequestProperty("Host", url);
        for(Entry<String, String> e: headers.entrySet()) {
            uc.setRequestProperty(e.getKey(), e.getValue());
        }
        return uc;
    }
    public static URLConnection urlConnection(String url, String Referer) throws Exception {
        URLConnection uc = urlConnection(url);
        uc.setRequestProperty("Referer", Referer);
        return uc;
    }

    public static Connection jsoupConnection(String url) throws Exception {
        Connection con = Jsoup.connect(url);
        con.header("Host", url);
        con.headers(headers);
        return con;
    }
    public static Connection jsoupConnection(String url, String Referer) throws Exception {
        Connection con = jsoupConnection(url);
        con.header("Referer", Referer);
        return con;
    }
}