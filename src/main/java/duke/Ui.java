package duke;

import java.util.Scanner;

/**
 * Ui handles interactions with the user.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class Ui {
    private static final String LINE = "--------------------------------------------------";

    private Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the input the user has provided.
     *
     * @return The line containing the user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the LINE divider.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        System.out.printf("%s%n%s%n%s%n%s%n", LINE, "Hello! I'm Cortana", "What can I do for you?", LINE);
    }

    /**
     * Displays the farewell message.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    /**
     * Displays the message indicating the given Task has been marked as completed.
     *
     * @param task String representation of the Task.
     */
    public void showMark(String task) {
        System.out.printf("Nice! I've marked this task as done:%n%s%n", task);
    }

    /**
     * Displays the message indicating the given Task has been marked as uncompleted.
     *
     * @param task String representation of the Task.
     */
    public void showUnmark(String task) {
        System.out.printf("OK, I've marked this task as not done yet:%n%s%n", task);
    }

    /**
     * Displays the message indicating the given Task has been added.
     *
     * @param task String representation of the Task.
     * @param size Current size of the list.
     */
    public void showAdd(String task, int size) {
        System.out.printf("Got it. I've added this task:%n%s%nNow you have %d task%s in the list.%n",
                task, size, size > 1 ? "s" : "");
    }

    /**
     * Displays the message indicating the given Task has been deleted.
     *
     * @param task String representation of the Task.
     * @param size Current size of the list.
     */
    public void showDelete(String task, int size) {
        System.out.printf("Noted. I've removed this task:%n%s%nNow you have %d task%s in the list.%n",
                task, size, size != 1 ? "s" : "");
    }

    /**
     * Displays the error message when the data file does not exist.
     */
    public void showLoadingError() {
        System.out.println("No saved data found.");
    }

    /**
     * Displays the given message.
     *
     * @param message Given message.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
}
