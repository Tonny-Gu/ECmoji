package proj.ecmoji.data;

import java.util.ArrayList;
import java.util.List;

public class ScoredProduct extends Product {
    private final List<ScoredComment> comments; 
    private long latestCommentTime;
    private long earliestCommentTime;
    private int lastGotPage;

    public ScoredProduct(List<ScoredComment> comments,
            long latestCommentTime, long earliestCommentTime, int lastGotPage, Product product) {
        super(product.getName(), product.getURL());
        this.latestCommentTime = latestCommentTime;
        this.earliestCommentTime = earliestCommentTime;
        this.comments = new ArrayList<>();
        this.comments.addAll(comments);
        this.lastGotPage = lastGotPage;
    }
    public ScoredProduct(List<ScoredComment> comments,
            long latestCommentTime, long earliestCommentTime, Product product) {
        this(comments, latestCommentTime, earliestCommentTime, -1, product);
    }
    public long getLatestCommentTime() {return latestCommentTime;}
    public long getEarliestCommentTime() {return earliestCommentTime;}
    public void setLatestCommentTime(long latestCommentTime) {
        this.latestCommentTime = latestCommentTime;
    }
    public void setEarliestCommentTime(long earliestCommentTime) {
        this.earliestCommentTime = earliestCommentTime;
    }
    public List<ScoredComment> getComments() {return comments;}
    public int getLastGotPage() {return lastGotPage;}
    public void setLastGotPage(int lastGotPage) {this.lastGotPage = lastGotPage;}
}