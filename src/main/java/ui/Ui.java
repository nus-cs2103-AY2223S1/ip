package ui;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private static final String BORDER = "_".repeat(60);
    private final Scanner sc;

    /**
     * Constructs a {@link Ui} object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Scans for user input.
     *
     * @return User input line in a string.
     */
    public String readInput() {
        return this.sc.nextLine();
    }

    /**
     * Formats Bocil's response in a specific format.
     *
     * @param input Response of Bocil that needs to be formatted.
     * @return Output in a bordered format.
     */
    public String prettifyOutput(String input) {
        return String.format("%s\n%s\n%s\n", BORDER, input, BORDER);
    }

    /**
     * Prints the formatted Bocil's response.
     *
     * @param output Formatted response of Bocil.
     */
    public void printOutput(String output) {
        System.out.println(prettifyOutput(output));
    }

    /**
     * Shows the error message of the exception.
     *
     * @param e Exception being handled.
     * @return Message of the error.
     */
    public String showError(Exception e) {
        return e.getMessage();
    }

    /**
     * Checks if the input is the terminating input.
     *
     * @param input String input line to be checked.
     * @return The truth value of the check.
     */
    public boolean isEnd(String input) {
        return input.equals("bye");
    }
}
