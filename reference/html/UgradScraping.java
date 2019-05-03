//package spring2019.lab_a3;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map.Entry;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UgradScraping {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("http://www.cs.princeton.edu/people/ugrad").get();
        Elements items = doc.select("div.people-ugrad").select("li");
        ArrayList<String> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(Element e: items) {
           //System.out.println(e.text());
           list.add(e.text());
        }
        Pattern pattern = Pattern.compile("(?<firstname>.+), (?<lastname>.+) '(?<age>\\d+)");
        for(String str: list) {
            if(str.length() == 0) continue;
            Matcher matcher = pattern.matcher(str);
            while(matcher.find()) {
                int age = Integer.valueOf( matcher.group("age") );
                System.out.printf("%s %s, %d\n", 
                    matcher.group("firstname"),
                    matcher.group("lastname"),
                    age);
                if(map.containsKey(age)) {
                    map.put(age, map.get(age)+1);
                } else {
                    map.put(age, 1);
                }
            }
        }
        for(Entry<Integer, Integer> e: map.entrySet()) {
            System.out.println("Graduating year:" + e.getKey() + ", Student number:" + e.getValue() );
        }
    }
}
