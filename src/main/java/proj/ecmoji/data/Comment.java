package proj.ecmoji.data;

import java.util.Map;

public class Comment {
    private String text;
    private double prob;
    private Map<Integer, Double> score;

    public Comment(String text) {
        setText(text);
    }

    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
    public Map<Integer, Double> getScore() {return score;}
    public void setScore(Map<Integer, Double> score) {this.score=score;}
    public double getProb() {return prob;}
    public void setProb(double prob) {this.prob = prob;}
    
}