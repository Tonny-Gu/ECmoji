package proj.ecmoji.data;

import java.util.Map;
import java.util.TreeMap;

public class DeepmojiScore {
    private String text;
    private Map<String, Double> score;
    public DeepmojiScore(String text, Map<String, Double> score) {
        this.text = text;
        this.score = new TreeMap<>();
        score.putAll(score);
    }
    public String getText() {return text;}
    public Map<String, Double> getScore() {return score;}
    @Override
    public String toString() {
        return text;
    }
}