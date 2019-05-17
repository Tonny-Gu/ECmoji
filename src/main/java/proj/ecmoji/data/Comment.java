package proj.ecmoji.data;

import java.util.ArrayList;
import java.util.List;

import proj.ecmoji.util.TimeFormatConverter;

public class Comment {
    private final String content;
    private final List<String> imageURL, videoURL;
    private final long commentTime;
    private final String ownerName, ownerImage;
    private final String productSize, productColor;

    public Comment(String content,
            List<String> imageURL, List<String> videoURL,
            long commentTime, String ownerName, String ownerImage,
            String productSize, String productColor) {
        this.content = content;
        this.imageURL = new ArrayList<>();
        this.imageURL.addAll(imageURL);
        this.videoURL = new ArrayList<>();
        this.videoURL.addAll(videoURL);
        this.commentTime = commentTime;
        this.ownerName = ownerName;
        this.ownerImage = ownerImage;
        this.productSize = productSize;
        this.productColor = productColor;
    }
    public Comment(String content) {
        this(content, null, null, System.currentTimeMillis(), "", "", "", "");
    }

    public String getContent() {return content;}

    public List<String> getImageURL() {return imageURL;}
    public List<String> getVideoURL() {return videoURL;}

    public long getCommentTime() {return commentTime;}
    public String getOwnerName() {return ownerName;}
    public String getOwnerImage() {return ownerImage;}
    public String getProductSize() {return productSize;}
    public String getProductColor() {return productColor;}
    
    @Override
    public int hashCode() {
        return content.hashCode() + ownerName.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        return this.getContent().equals(((Comment)obj).getContent())
            && this.getOwnerName().equals(((Comment)obj).getOwnerName());
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
