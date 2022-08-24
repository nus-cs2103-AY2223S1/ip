package duke;

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
    public static void endMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Message shown when a task is deleted.
     * @param task task to be deleted.
     */
    public static void deleteTask(Task task) {
        System.out.println("Noted. I've removed this task:" + "\n" + "\t" + task.toString());
    }

    /**
     * Message shown when a task is marked.
     * @param task Task to be marked.
     */
    public static void markTask(Task task) {
        System.out.println("Nice! I've marked this task as done:" + "\n" + "\t" + task.toString());
    }

    /**
     * Message shown when a task is unmarked.
     * @param task Task to be unmarked.
     */
    public static void unmarkTask(Task task) {
        System.out.println("Ok, I've marked this task as not done yet:" + "\n" + "\t" + task.toString());
    }

    /**
     * Message shown when a task is added.
     * @param task Task to be added.
     */
    public static void addTask(Task task) {
        System.out.println("Got it. I've added this task:" + "\n\t" + task.toString());
    }

    /**
     * Message shown when the number of tasks has to be shown to the user.
     * @param numOfTasks Number of tasks left in the list.
     */
    public static void numOfTasks(int numOfTasks) {
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }
}
