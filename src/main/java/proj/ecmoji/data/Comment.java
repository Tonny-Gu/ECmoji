package proj.ecmoji.data;

import java.util.ArrayList;
import java.util.List;

import proj.ecmoji.util.TimeFormatConverter;

public class Comment {
    private final String content;
    private final List<String> imageURL, videoURL;
    private final long commentTime;
    private final String ownerName, ownerImage;

    public Comment(String content,
            List<String> imageURL, List<String> videoURL,
            long commentTime, String ownerName, String ownerImage) {
        this.content = content;
        this.imageURL = new ArrayList<>();
        this.imageURL.addAll(imageURL);
        this.videoURL = new ArrayList<>();
        this.videoURL.addAll(videoURL);
        this.commentTime = commentTime;
        this.ownerName = ownerName;
        this.ownerImage = ownerImage;
    }
    public Comment(String content) {
        this(content, null, null, System.currentTimeMillis(), "", "");
    }

    public String getContent() {return content;}

    public List<String> getImageURL() {return imageURL;}
    public List<String> getVideoURL() {return videoURL;}

    public long getCommentTime() {return commentTime;}
    public String getOwnerName() {return ownerName;}
    public String getOwnerImage() {return ownerImage;}

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String toString() {
        try {
            return ownerName + ": " + content + " @ " + TimeFormatConverter.LongToJDFormat(commentTime);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        
    }
}
