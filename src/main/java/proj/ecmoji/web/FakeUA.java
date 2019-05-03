package proj.ecmoji.web;

import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class FakeUA {
    static {
        System.setProperty("http.agent", "");
    }
    public static URLConnection urlConnection(String url) throws Exception {
        URL u = new URL(url);
        URLConnection uc = u.openConnection();
        uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
        return uc;
    }
    public static Connection jsoupConnection(String url) throws Exception {
        Connection con = Jsoup.connect(url);
        con.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        con.header("Accept-Encoding", "gzip, deflate");
        con.header("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
        con.header("Connection", "keep-alive");
        con.header("Host", url);
        con.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
        return con;
    }
}