package proj.ecmoji.web;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonArray;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import proj.ecmoji.data.Comment;

public class JDParser {
    final private static String commentsURL = "https://sclub.jd.com/comment/productPageComments.action?callback=fetchJSON_comment98vv580&productId=%d&score=0&sortType=6&page=%d&pageSize=10&isShadowSku=0&fold=1";
    public static long getProductID(String productURL) throws Exception {
        Pattern pattern = Pattern.compile("item.jd.com/(?<id>\\d+).html");
        Matcher matcher = pattern.matcher(productURL);
        while(matcher.find()) {
            return Long.valueOf(matcher.group("id"));
        }
        throw new Exception("Invalid JD Product Page URL");
    }
    public static String getCommentURL(long productID, int page) {
        return String.format(commentsURL, productID, page);
    }
    public static String getCommentsJSON(String commentURL) throws Exception {
        Connection jsoup = FakeUA.jsoupConnection(commentsURL, commentURL);
        Document page = jsoup.get();
        Pattern pattern = Pattern.compile("^[\\w]+?\\((?<json>.+)\\);$");
        Matcher matcher = pattern.matcher(page.body().text());
        while(matcher.find()) {
            return matcher.group("json");
        }
        throw new Exception("Failed to parse comments from JSON");
    }
    public static List<Comment> getComments(String commentURL) throws Exception {
        ArrayList<Comment> comments = new ArrayList<>();
        //new Comment(content, imageURL, videoURL, commentTime, ownerName, ownerImage)
         
        return comments;
    }
    public static void main(String[] args) throws Exception{

    }
}