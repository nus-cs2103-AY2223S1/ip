package main.java;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    /**
     * 'List' object attribute to store String inputs given.
     */
    private static List<Task> taskList = new ArrayList<>();

    /**
     * Method to add String input to 'inputList'.
     * @param input String object to be added to 'inputList'.
     */
    public void add(String input) {
        Task newTask = new Task(input);
        taskList.add(newTask);
    }

    /**
     * Override 'toString' method of 'TaskList' object.
     */
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < taskList.toArray().length; i++) {
            res += String.format("%d. %s%n", i + 1, taskList.get(i));
        }
        return res;
    }

    /**
     * Mark 'Task' at given index as done.
     * @param index Index of task to be done. 1 based indexing.
     */
    public void markDone(int index) {
        this.taskList.get(index - 1).markDone();
        System.out.printf("Nice! I've marked this task as done:%n   %s%n",
                taskList.get(index - 1));
    }

    /**
     * Mark 'Task' at given index as undone.
     * @param index Index of task to be undone. 1 based indexing.
     */
    public void markUnDone(int index) {
        this.taskList.get(index - 1).markUnDone();
        System.out.printf("OK, I've marked this task as not done yet:%n   %s%n",
                taskList.get(index - 1));
    }
}