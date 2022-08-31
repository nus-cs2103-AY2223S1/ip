package seedu.duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the user interface of the program.
 */
public class Ui {

    /* Greeting message */
    private final String WELCOME = "Hello! I'm Luke\nWhat can I do for you?";
    /* Message to be displayed when a task is added */
    private final String ADDED_TASK = "Got it. I've added this task:";
    /* Message to be displayed when a task is removed */
    private final String REMOVED_TASK = "Noted. I've removed this task:";
    /* A scanner object to scan user input */
    private Scanner scanner;

    /**
     * A constructor for Ui
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the greeting message.
     */
    public void showWelcome() {
        System.out.println(WELCOME);
    }

    /**
     * Reads the input from the command line from the user.
     *
     * @return the input with prevailing spaces removed.
     */
    public String readCommand() {
        String input = scanner.nextLine().strip();
        return input;
    }

    /**
     * Prints message after adding a task
     *
     * @param task The task added.
     */
    public void add(Task task) {
        System.out.println(String.format("%s\n%s", ADDED_TASK, task));
    }

    /**
     * Prints the message after removing a task.
     *
     * @param task The task to be removed.
     */
    public void remove(Task task) {
        System.out.println(String.format("%s\n%s", REMOVED_TASK, task));
    }

    /**
     * Prints the exit message.
     */
    public void showGoodbye() {
        System.out.println("Bye! Thanks for using Luke!");
        scanner.close();
    }

    /**
     * Prints Duke Exception message.
     *
     * @param e Duke exception.
     */
    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints IOException message.
     *
     * @param e IOException.
     */
    public void showLoadingError(IOException e) {
        System.out.println(e.getMessage());
    }

}
