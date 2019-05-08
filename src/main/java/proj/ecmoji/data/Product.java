package proj.ecmoji.data;

import java.util.ArrayList;
import java.util.List;

public class Product{
    private final String name, url;
    private final List<ScoredComment> comments; 
    private long latestCommentTime;
    private long earliestCommentTime;
    public Product(String name, String url, List<ScoredComment> comments,
            long latestCommentTime, long earliestCommentTime) {
        this.name = name;
        this.url = url;
        this.latestCommentTime = latestCommentTime;
        this.earliestCommentTime = earliestCommentTime;
        this.comments = new ArrayList<>();
        this.comments.addAll(comments);
    }

    public String getName() {return name;}
    public String getURL() {return url;}
    public long getLatestCommentTime() {return latestCommentTime;}
    public long getEarliestCommentTime() {return earliestCommentTime;}
    public void setLatestCommentTime(long latestCommentTime) {
        this.latestCommentTime = latestCommentTime;
    }
    public void setEarliestCommentTime(long earliestCommentTime) {
        this.earliestCommentTime = earliestCommentTime;
    }
    public List<ScoredComment> getComments() {return comments;}

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}