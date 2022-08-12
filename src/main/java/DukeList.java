package main.java;

import java.util.ArrayList;
import java.util.List;

public class DukeList {
    /**
     * 'List' attribute to store inputs.
     */
    private static List<String> inputList = new ArrayList<>();

    public void add(String input) {
        inputList.add(input);
    }

    public void print() {
        for (int i = 0; i < inputList.toArray().length; i++) {
            System.out.printf("%d. %s%n", i + 1, inputList.get(i));
        }
    }
}
