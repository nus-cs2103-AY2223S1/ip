package ui;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {

    private static final String BORDER = "_".repeat(60);
    private final String name;
    private final Scanner sc;

    /**
     * Constructs a {@link Ui} object.
     *
     * @param name Name of the UI.
     */
    public Ui(String name) {
        this.name = name;
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
     * Formats the Duke's response in a specific format.
     *
     * @param input Response of the Duke that needs to be formatted.
     * @return Output in a bordered format.
     */
    public String prettifyOutput(String input) {
        return String.format("%s\n%s\n%s\n", BORDER, input, BORDER);
    }

    /**
     * Prints the formatted Duke's response.
     *
     * @param output Formatted response of the Duke.
     */
    public void printOutput(String output) {
        System.out.println(prettifyOutput(output));
    }

    /**
     * Prints the introduction line of the UI.
     */
    public void introduce() {
        String line1 = String.format("Hello! I'm %s", this.name);
        String line2 = "What can I do for you?";
        String join = String.join("\n", line1, line2);
        this.printOutput(join);
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
