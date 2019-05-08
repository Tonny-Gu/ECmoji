package proj.ecmoji.data;

import java.util.List;

public class Product{
    private String name, url;
    private List<Comment> comments; 
    private long latestCommentTime;
    private long earliestCommentTime;
}