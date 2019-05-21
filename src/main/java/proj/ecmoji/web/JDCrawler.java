package proj.ecmoji.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public static ScoredProduct getComments(Product product, int page) throws Exception {
        return getComments(product, page, page+1);
    }
    public static ScoredProduct getAllComments(Product product) throws Exception {
        return getComments(product, 0, JDParser.getCommentsMaxPage(product.getURL()));
    }
    public static ScoredProduct getAllComments(String productURL) throws Exception {
        return getAllComments( JDParser.getProductInfo(productURL) );
    }
    public static ScoredProduct updateComment(ScoredProduct product) throws Exception {
        ScoredProduct newProduct = product.clone();
        for(int page=0; page<JDParser.getCommentsMaxPage(product.getURL()); ++page) {
            ScoredProduct partical = getComments(product, page);
            if(partical.getLatestCommentTime()>newProduct.getLatestCommentTime()) {
                newProduct.setLatestCommentTime( partical.getLatestCommentTime() );
            }
            boolean exitFlag = false;
            for(ScoredComment comment: partical.getComments()) {
                if(comment.getCommentTime()>product.getLatestCommentTime()) newProduct.getComments().add(comment);
                else exitFlag = true;
            }
            if(exitFlag) break;
        }
        return newProduct;
    }
    public static void updateCommentsInStorage() throws Exception {
        Set<ScoredProduct> products = StorageServer.getProductList();
        for(ScoredProduct product: products) {
            System.out.printf("======Current Product: %s======\n", product.toString());
            products.remove(product);
            products.add(updateComment(product));
        }
    }
    public static void main(String[] args) throws Exception {
        StorageServer.load();
        updateCommentsInStorage();
        StorageServer.save();
    }
}