package proj.ecmoji.web;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import proj.ecmoji.data.Comment;
import proj.ecmoji.util.TimeFormatConverter;

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
    public static String getCommentURL(String productURL, int page) throws Exception {
        return getCommentURL(getProductID(productURL), page);
    }
    public static String getCommentsJSON(String commentURL) throws Exception {
        Connection jsoup = FakeUA.jsoupConnection(commentURL, commentURL);
        Document page = jsoup.get();
        Pattern pattern = Pattern.compile("^[\\w]+?\\((?<json>.+)\\);$");
        Matcher matcher = pattern.matcher(page.body().text());
        while(matcher.find()) {
            return matcher.group("json");
        }
        throw new Exception("Failed to parse comments from JSON");
    }
    private static List<String> getStringsInObjArray(JsonObject comment, String listName, String elementName) {
        List<String> strings = new ArrayList<>();
        if(!comment.has(listName)) return strings;

        JsonArray array = comment.get(listName).getAsJsonArray();
        for(int i=0; i<array.size(); ++i) {
            strings.add(
                array.get(i).getAsJsonObject()
                    .get(elementName).getAsString()
            );
        }
        return strings;
    }
    public static List<Comment> getComments(String commentURL) throws Exception {
        List<Comment> comments = new ArrayList<>();
        //new Comment(content, imageURL, videoURL, commentTime, ownerName, ownerImage)
        JsonArray array = new JsonParser().parse( getCommentsJSON(commentURL) ).getAsJsonObject()
            .get("comments").getAsJsonArray();
        for(int i=0; i<array.size(); ++i) {
            JsonObject obj = array.get(i).getAsJsonObject();
            String content = obj.get("content").getAsString();
            String ownerName = obj.get("nickname").getAsString();
            String ownerImage = obj.get("userImage").getAsString();
            long commentTime = TimeFormatConverter.JDFormatToLong(
                obj.get("creationTime").getAsString()
            );
            List<String> imageURL = getStringsInObjArray(obj, "images", "imgUrl");
            List<String> videoURL = getStringsInObjArray(obj, "videos", "remark");
            comments.add( new Comment(content, imageURL, videoURL, commentTime, ownerName, ownerImage) );
        }
        return comments;
    }
    public static void main(String[] args) throws Exception {
        List<Comment> comments = getComments( getCommentURL("https://item.jd.com/5089255.html#comment", 2));
        comments.forEach(e->{
            System.out.println(e);
            e.getVideoURL().forEach(System.out::println);
        });
    }
}