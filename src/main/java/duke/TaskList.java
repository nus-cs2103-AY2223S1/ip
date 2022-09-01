package duke;

import java.util.ArrayList;

/**
 * A custom class to keep track of the tasks to be done
 *
 * @author eugeneleong
 * @version 1.0
 */

public class TaskList {

    private static final String LINE = "-----------------------------";
    private ArrayList<Task> tasks;

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
        System.out.println(LINE + "\n");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println((i + 1) + "." + t);
        }
        System.out.println(LINE + "\n");
    }

    /**
     * Finds all the tasks which contain the specified keyword and prints them out
     * @input keyword to check
     */
    public void findTask(String keyword) {
        ArrayList<Task> matched = new ArrayList<>();
        System.out.println(LINE + "\n");
        System.out.println("Here are the matching tasks in your list:");
        for (Task t : tasks) {
            if (t.getAction().contains(keyword)) {
                matched.add(t);
            }
        }
        for (int j = 0; j < matched.size(); j++) {
            Task task = matched.get(j);
            System.out.println((j + 1) + "." + task);
        }
    }
    /**
     * Marks a task (with a specified index) as "Done"
     * @input idx of task in TaskList
     */
    public void mark(int idx) {
        Task task = tasks.get(idx);
        task.markIsDone();
        System.out.println(LINE + "\n"
                + "Nice! I've marked this task as done:\n"
                + "[X] " + task + "\n"
                + LINE + "\n");
    }

    /**
     * Marks a task (with a specified index) as "Undone"
     * @input index of task in TaskList
     */
    public void unmark(int idx) {
        Task task = tasks.get(idx);
        task.unmarkIsDone();
        System.out.println(LINE + "\n"
                + "OK, I've marked this task as not done yet:\n"
                + "[ ] " + task + "\n"
                + LINE + "\n");
    }

    /**
     * Adds a task into TaskList
     * @input task to be added into TaskList
     */
    public void add(Task task) {
        tasks.add(task);
        System.out.println(LINE + "\n"
                + "Got it. I've added this task: " + "\n"
                + task + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n"
                + LINE + "\n");
    }

    /**
     * Removes a task (with specified index) from TaskList
     * @input index of task to be removed from TaskList
     */
    public void delete(int idx) {
        Task task = tasks.remove(idx);
        System.out.println(LINE + "\n" + "Noted. I've removed this task:" + "\n"
                + task + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n"
                + LINE + "\n");
    }
}
