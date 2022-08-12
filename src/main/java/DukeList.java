package main.java;

import java.util.ArrayList;
import java.util.List;

public class DukeList {
    /**
     * 'List' object attribute to store String inputs given.
     */
    private static List<String> inputList = new ArrayList<>();

    /**
     * Method to add String input to 'inputList'.
     * @param input String object to be added to 'inputList'.
     */
    public void add(String input) {
        inputList.add(input);
    }

    /**
     * Method to print inputs stored in 'inputList'.
     */
    public void print() {
        for (int i = 0; i < inputList.toArray().length; i++) {
            System.out.printf("%d. %s%n", i + 1, inputList.get(i));
        }
    }
}
