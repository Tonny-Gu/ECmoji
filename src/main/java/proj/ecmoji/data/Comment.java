package proj.ecmoji.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Comment {
    private final String content;
    //private final String engText;
    //private final Map<Integer, Double> score;
    private final List<String> imageURL, videoURL;
    private final long commentTime;
    private final String ownerName, ownerImage;

    public Comment(String content, String engText,
            List<String> imageURL, List<String> videoURL,
            long commentTime, String ownerName, String ownerImage) {
        //this.engText = engText;
        this.content = content;
        //this.score = score;
        //this.imageURL = imageURL;
        //this.videoURL = videoURL;
        this.imageURL = new ArrayList<>();
        this.imageURL.addAll(imageURL);
        this.videoURL = new ArrayList<>();
        this.videoURL.addAll(videoURL);
        this.commentTime = commentTime;
        this.ownerName = ownerName;
        this.ownerImage = ownerImage;
    }
    public Comment(String content) {
        this(content, null, null, null, System.currentTimeMillis(), "", "");
    }
    //public String getEngText() {return engText;}
    //public void setEngText(String engText) {this.engText = engText;}

    public String getContent() {return content;}
    //public void setContent(String content) {this.content = content;}

    public List<String> getImageURL() {return imageURL;}
    public List<String> getVideoURL() {return videoURL;}
    //public void setImageURL(List<String> imageURL) {this.imageURL=imageURL;}
    //public void setVideoURL(List<String> videoURL) {this.videoURL=videoURL;}

    public Map<Integer, Double> getScore() {return score;}
    //public void setScore(Map<Integer, Double> score) {this.score=score;}

    public long getCommentTime() {return commentTime;}
    //public void setCommentTime(long commentTime) {this.commentTime = commentTime;}
    
    public String getOwnerName() {return ownerName;}
    public String getOwnerImage() {return ownerImage;}
    //public void setOwnerName(String ownerName) {this.ownerName=ownerName;}
    //public void setOwnerImage(String ownerImage) {this.ownerImage=ownerImage;}

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}