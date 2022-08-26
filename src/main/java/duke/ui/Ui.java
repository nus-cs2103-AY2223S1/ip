package duke.ui;

import java.util.Scanner;

/**
 * Represents a tool that deals with interactions with the user.
 */
public class Ui {

    private Scanner s = new Scanner(System.in);
    private String input;

    /**
     * Shows the welcome message when duke is initiated.
     */
    public void showWelcome() {
        System.out.println("Just a moment...\nHello! I am Duke.");
        System.out.println("What can I do for you?");
    }

    /**
     * Shows the message of loading error to the user.
     */
    public void showLoadingError() {
        System.out.println("Unable to read the specified file.");
        System.out.println("New file created.");
    }

    /**
     * Reads and stores the user input.
     *
     * @return
     */
    public String readCommand() {
        this.input = s.nextLine();
        return this.input;
    }

    /**
     * Shows the error message to the user.
     *
     * @param message A string.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Shows the divider line to the user.
     */
    public void showLine() {
        System.out.println("-------------------");
    }

    /**
     * Shows the message to the user when a command is executed successfully.
     *
     * @param message Message of success.
     */
    public void showSuccessMessage(String message) {
        System.out.println(message);
    }
}
