package proj.ecmoji.ml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import proj.ecmoji.data.Comment;
import proj.ecmoji.data.DeepmojiScore;
import proj.ecmoji.data.ScoredComment;
import proj.ecmoji.trans.Translator;
import proj.ecmoji.web.JDParser;

public class CommentScorer {
    private static List<ScoredComment> scoreCommentSerialSending(List<Comment> comments) throws Exception {
        List<ScoredComment> scoredComments = new ArrayList<>();
        comments.forEach(e->{
            try {
                String engText = Translator.translateToEnglish(e.getContent());
                ScoredComment comment = new ScoredComment(engText, Deepmoji.scoreSentence(engText).getScore(), e);
                scoredComments.add( comment );
            } catch (Exception ex) {throw new RuntimeException(ex);}
        });
        return scoredComments;
    }
    private static List<ScoredComment> scoreCommentParallelSending(List<Comment> comments) throws Exception {
        List<ScoredComment> scoredComments = new ArrayList<>();
        Map<String, String> engTexts = new HashMap<>();
        comments.forEach(e->engTexts.put(e.getContent(), Translator.translateToEnglish(e.getContent())));
        List<DeepmojiScore> testResult = Deepmoji.scoreSentences(new ArrayList<>(engTexts.values()));
        Map<String, Map<String, Double>> scores = new HashMap<>();
        testResult.forEach(e->scores.put(e.getText(), e.getScore()));
        comments.forEach(e->{
            try {
                scoredComments.add( new ScoredComment(
                    engTexts.get(e.getContent()),
                    scores.get(engTexts.get(e.getContent())),
                    e) );
            } catch (Exception ex) {throw new RuntimeException(ex);}
        });
        return scoredComments;
    }
    public static List<ScoredComment> scoreComment(List<Comment> comments) throws Exception {
        return scoreCommentSerialSending(comments);
    }
    public static void main(String[] args) throws Exception {
        List<ScoredComment> scoredComments = scoreComment(JDParser.getComments("https://item.jd.com/5089255.html#comment", 2));
        scoredComments.forEach(e->System.out.println(e.getEngText() + e.getScore().size()));
    }
}