package main.java.duke;

import java.util.ArrayList;

/**
 * A custom class to keep track of the tasks to be done
 *
 * @author eugeneleong
 * @version 1.0
 */

public class TaskList {

    private ArrayList<Task> tasks;
    private static final String line = "-----------------------------";

    /**
     * If there are no pre-existing Tasklist, create a new Tasklist
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with pre-defined tasks
     * @input pre-existing list of tasks
     */
    public TaskList(ArrayList<Task> ls) {
        this.tasks = ls;
    }

    /**
     * Prints out all the tasks in the current TaskList
     */
    public void listAll() {
        System.out.println(line + "\n");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println((i + 1) + "." + t);
        }
        System.out.println(line + "\n");
    }

    /**
     * Marks a task (with a specified index) as "Done"
     * @input idx of task in TaskList
     */
    public void mark(int idx) {
        Task t = tasks.get(idx);
        t.markIsDone();
        System.out.println(line + "\n"
                + "Nice! I've marked this task as done:\n"
                + "[X] " + t + "\n" +
                line + "\n");
    }

    /**
     * Marks a task (with a specified index) as "Undone"
     * @input index of task in TaskList
     */
    public void unmark(int idx) {
        Task t = tasks.get(idx);
        t.unmarkIsDone();
        System.out.println(line + "\n"
                + "OK, I've marked this task as not done yet:\n"
                + "[ ] " + t + "\n"
                + line + "\n");
    }

    /**
     * Adds a task into TaskList
     * @input task to be added into TaskList
     */
    public void add(Task t) {
        tasks.add(t);
        System.out.println(line + "\n" +
                "Got it. I've added this task: " + "\n"
                + t + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.\n"
                + line + "\n");
    }

    /**
     * Removes a task (with specified index) from TaskList
     * @input index of task to be removed from TaskList
     */
    public void delete(int idx) {
        Task t = tasks.remove(idx);
        System.out.println(line + "\n" + "Noted. I've removed this task:" + "\n"
                + t + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.\n" +
                line + "\n");
    }
}
