package duke;

import java.util.Scanner;

/**
 * The Ui class handles the output to be printed by Bob that is shown to the user.
 */
public class Ui {
    private static final String horizontalLine = "_____________________________________________________________";
    private Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the user input.
     *
     * @return The line containing the user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the horizontal line (divider).
     */
    public void printHorizontalLine() {
        System.out.println(horizontalLine);
    }

    /**
     * Prints the initial welcome message to be shown to the user.
     */
    public void printWelcomeMessage() {
        System.out.printf("Hey there! I'm Bob\nWhat can I do for you?\n");
        printHorizontalLine();
    }

    /**
     * Prints the bye message to the user.
     */
    public void printByeMessage() {
        System.out.println("Bye! Hope to see you again soon!");
        sc.close();
    }

    /**
     * Prints the mark message as well as the task that has been marked.
     *
     * @param task The task that has been marked.
     */
    public void printMark(String task) {
        System.out.println("Nice! I have marked this Bob task as done:");
        System.out.println(task);
    }

    /**
     * Prints the message as well the task that has been unmarked.
     *
     * @param task The task that has been unmarked.
     */
    public void printUnmark(String task) {
        System.out.println("OK, I've marked this Bob task as not done yet:");
        System.out.println(task);
    }

    /**
     * Prints the message that task has been added.
     *
     * @param add String representation of added task.
     * @param size Size of list after task is added.
     */
    public void printAdd(String add, int size) {
        System.out.println("Got it. I've added this Bob task:");
        System.out.println(add);
        System.out.println(String.format("Now you have %d Bob task%s in the list.", size, size != 1 ? "s" : ""));
    }

    /**
     * Prints message of task being deleted.
     *
     * @param delete String representation of deleted task.
     * @param size The size of list after task is deleted.
     */
    public void printDelete(String delete, int size) {
        System.out.println("Noted. I've removed this bob task:");
        System.out.println(delete);
        System.out.println(String.format("Now you have %d Bob task%s in the list.", size, size != 1 ? "s" : ""));
    }

    /**
     * Prints loading error if file does not exist.
     */
    public void printLoadingError() {
        System.out.println("No saved data found");
    }

    /**
     * Prints any given message.
     *
     * @param message Given message.
     */
    public void printAnyOtherMessage(String message) {
        System.out.println(message);
    }
}
