package cleverNotBot;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UI {

    public UI(){}

    public void chat(String text){
        List<String> textLines;
        int max;
        if (text == null){
            textLines = Collections.singletonList("null");
            max = 4;
        } else {
            textLines = Arrays.asList(text.split("\n"));
            max = textLines.stream().map(String::length).max(Integer::compareTo).get();
        }
        System.out.println(" " + "_".repeat(max + 10));
        System.out.println("|" + " ".repeat(max + 10) + "|");
        for (int i = 0; i < textLines.size(); i++) {
            int curr = textLines.get(i).length();
            System.out.println("|" + " ".repeat(5) + textLines.get(i) + " ".repeat(5 + max-curr) + "|");
        }
        System.out.println("|" + "_".repeat(max + 10) + "|");

    }

    public String coverText(String text){
        List<String> textLines;
        int max;
        if (text == null){
            textLines = Collections.singletonList("null");
            max = 4;
        } else {
            textLines = Arrays.asList(text.split("\n"));
            max = textLines.stream().map(String::length).max(Integer::compareTo).get();
        }
        StringBuilder op = new StringBuilder();
        op.append(" ").append("_".repeat(max + 10));
        op.append("|").append(" ".repeat(max + 10)).append("|");
        for (int i = 0; i < textLines.size(); i++) {
            int curr = textLines.get(i).length();
            op.append("|").append(" ".repeat(5)).append(textLines.get(i)).append(" ".repeat(5 + max-curr)).append("|");
        }
        op.append("|").append("_".repeat(max + 10)).append("|");
        return op.toString();
    }
}
