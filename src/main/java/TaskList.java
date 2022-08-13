package main.java;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    /**
     * 'List' object attribute to store String inputs given.
     */
    private static List<Task> taskList = new ArrayList<>();

    /**
     * Get description as String from input.
     * @param input String of words given by user as input
     * @return      Return description of input task.
     */
    private String getDescription(String input) {
        if (input.startsWith("deadline")) {
            return input.substring(9, input.indexOf("/by ") - 1);
        } else if (input.startsWith("event")) {
            return input.substring(6, input.indexOf("/at ") - 1);
        } else if (input.startsWith("todo")){
            return input.substring(5);
        } else {
            return input;
        }
    }

    /**
     * Get date as String from input.
     * @param input String of words given by user as input
     * @return      Return date of input task.
     */
    private String getDate(String input) {
        if (input.startsWith("deadline")) {
            return input.substring(input.indexOf("/by ") + 4);
        } else if (input.startsWith("event")) {
            return input.substring(input.indexOf("/at ") + 4);
        } else {
            return null;
        }
    }

    /**
     * Method to add String input to 'inputList'.
     * @param input String object to be added to 'inputList'.
     */
    public void add(String input) {
        String[] inputArr = input.split(" ");

        Task newTask;
        if (inputArr[0].equals("deadline")){
            newTask = new Deadline(getDescription(input), getDate(input));
        } else if (inputArr[0].equals("event")){
            newTask = new Event(getDescription(input), getDate(input));
        } else if (inputArr[0].equals("todo")){
            newTask = new ToDo(getDescription(input));
        } else {
            newTask = new Task(input);
        }

        taskList.add(newTask);
        int size = taskList.size();
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + newTask);
        System.out.printf("Now you have %d task%s in the list.%n%n",
                size, size == 1 ? "" : "s");
    }

    /**
     * Override 'toString' method of 'TaskList' object.
     */
    @Override
    public String toString() {
        String res = String.format("Here are the tasks in your list: %n");
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
        System.out.printf("Nice! I've marked this task as done:%n   %s%n%n",
                taskList.get(index - 1));
    }

    /**
     * Mark 'Task' at given index as undone.
     * @param index Index of task to be undone. 1 based indexing.
     */
    public void markUnDone(int index) {
        this.taskList.get(index - 1).markUnDone();
        System.out.printf(
                "OK, I've marked this task as not done yet:%n   %s%n%n",
                taskList.get(index - 1));
    }
}