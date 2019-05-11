package proj.ecmoji.data;

import java.util.LinkedHashMap;
import java.util.Map;

public class ScoredComment extends Comment{
    private final String engText;
    private final Map<String, Double> score;
    public ScoredComment(String engText, Map<String, Double> score, Comment comment) {
        super(comment.getContent(),
            comment.getImageURL(),
            comment.getVideoURL(),
            comment.getCommentTime(),
            comment.getOwnerName(),
            comment.getOwnerImage(),
            comment.getProductSize(),
            comment.getProductColor());
        this.engText = engText;
        this.score = new LinkedHashMap<>();
        this.score.putAll(score);
    }
    public String getEngText() {return engText;}
    public Map<String, Double> getScore() {return score;}
}