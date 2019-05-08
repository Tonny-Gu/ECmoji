package proj.ecmoji.data;

import java.util.LinkedHashMap;
import java.util.Map;

public class ScoredComment extends Comment{
    private final String engText;
    private final Map<Integer, Double> score;
    public ScoredComment(String engText, Map<Integer, Double> score, Comment comment) {
        super(comment.getContent(),
            comment.getImageURL(),
            comment.getVideoURL(),
            comment.getCommentTime(),
            comment.getOwnerName(),
            comment.getOwnerImage());
        this.engText = engText;
        this.score = new LinkedHashMap<>();
        score.putAll(score);
    }
    public String getEngText() {return engText;}
    public Map<Integer, Double> getScore() {return score;}
}