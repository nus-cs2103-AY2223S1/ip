package duke;

import duke.task.Task;

/**
 * The Ui class handles most of the messages shown to the user.
 */
public class Ui {
    /**
     * Constructor for Ui.
     */
    public Ui() {

    }

    /**
     * Message shown when save file is not found.
     */
    public static void showLoadingError() {
        System.out.println("No save file to load from");
    }

    /**
     * Message shown when starting Duke.
     */
    public static void startMessage() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
    }

    /**
     * Message shown when user stops using Duke.
     */
    public static String endMessage() {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Message shown when a task is deleted.
     * @param task task to be deleted.
     */
    public static String deleteTask(Task task) {
        return ("Noted. I've removed this task:" + "\n" + "\t" + task.toString());
    }

    /**
     * Message shown when a task is marked.
     * @param task Task to be marked.
     */
    public static String markTask(Task task) {
        return ("Nice! I've marked this task as done:" + "\n" + "\t" + task.toString());
    }

    /**
     * Message shown when a task is unmarked.
     * @param task Task to be unmarked.
     */
    public static String unmarkTask(Task task) {
        return ("Ok, I've marked this task as not done yet:" + "\n" + "\t" + task.toString());
    }

    /**
     * Message shown when a task is added.
     * @param task Task to be added.
     */
    public static String addTask(Task task) {
        return ("Got it. I've added this task:" + "\n\t" + task.toString());
    }

    /**
     * Message shown when the number of tasks has to be shown to the user.
     * @param numOfTasks Number of tasks left in the list.
     */
    public static String numOfTasks(int numOfTasks) {
        return ("Now you have " + numOfTasks + " tasks in the list.");
    }

    /**
     * Returns the String representing the Tasks to be printed.
     * @param list
     * @return String representing Tasks to be printed.
     */
    public static String printList(TaskList list) {
        StringBuilder print = new StringBuilder("Here are the tasks in your list:\n");
        print.append(list.toString());
        return print.toString();
    }
}
