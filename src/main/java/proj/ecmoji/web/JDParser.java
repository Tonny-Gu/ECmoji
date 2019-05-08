package proj.ecmoji.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;

public class JDParser {
    final static String commentsURL = "https://sclub.jd.com/comment/productPageComments.action?callback=fetchJSON_comment98vv580&productId=%d&score=0&sortType=6&page=%d&pageSize=10&isShadowSku=0&fold=1";
    public static void InfoPageParser(String productURL) throws Exception {
        
        Connection jsoup = FakeUA.jsoupConnection(commentsURL, productURL);
        Document page = jsoup.get();
        Pattern pattern = Pattern.compile("^[\\w]+?\\((?<json>.+)\\);$");
        Matcher matcher = pattern.matcher(page.body().text());
        while(matcher.find()) {
            System.out.println(matcher.group("json"));
        }
    }
    public static void main(String[] args) throws Exception{
    }
}