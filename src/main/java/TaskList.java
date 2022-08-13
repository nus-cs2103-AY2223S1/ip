package main.java;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    /**
     * 'List' object attribute to store String inputs given.
     */
    private static List<String> taskList = new ArrayList<>();

    /**
     * Method to add String input to 'inputList'.
     * @param input String object to be added to 'inputList'.
     */
    public void add(String input) {
        taskList.add(input);
    }

    /**
     * Method to print inputs stored in 'inputList'.
     */
    public void print() {
        for (int i = 0; i < taskList.toArray().length; i++) {
            System.out.printf("%d. %s%n", i + 1, taskList.get(i));
        }
    }
}
