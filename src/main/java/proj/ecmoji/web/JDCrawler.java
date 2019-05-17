package proj.ecmoji.web;

import java.util.ArrayList;
import java.util.List;

import proj.ecmoji.data.Comment;
import proj.ecmoji.data.Product;
import proj.ecmoji.data.ScoredComment;
import proj.ecmoji.data.ScoredProduct;
import proj.ecmoji.data.StorageServer;
import proj.ecmoji.ml.CommentScorer;

public class JDCrawler {
    public static ScoredProduct getComments(Product product, int minPage, int maxPage) throws Exception {
        List<ScoredComment> scoredComments = new ArrayList<>();
        for(int page=minPage; page<maxPage; ++page) {
            System.out.printf("======Current Page: %d======\n", page);
            List<Comment> comments = JDParser.getComments(product.getURL(), page);
            comments.forEach(System.out::println);
            scoredComments.addAll(CommentScorer.scoreComment(comments));
            //Thread.sleep(1200);
        }
        long latestCommentTime = scoredComments.get(0).getCommentTime();
        long earliestCommentTime = scoredComments.get(scoredComments.size()-1).getCommentTime();
        return new ScoredProduct(scoredComments, latestCommentTime, earliestCommentTime, maxPage, product);
    }
    public static ScoredProduct getAllComments(Product product) throws Exception {
        return getComments(product, 0, JDParser.getCommentsMaxPage(product.getURL()));
    }

    public static void updateCommentsInStorage() {

    }
    public static void main(String[] args) throws Exception {
        StorageServer.addProduct( getComments(JDParser.getProductInfo("https://item.jd.com/5089255.html#none"), 0, 50) );
        StorageServer.save();
    }
}