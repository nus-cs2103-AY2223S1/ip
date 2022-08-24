package ui;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {

    protected final String name;
    protected final Scanner sc;
    protected static final String BORDER = "____________________________________________________________";

    /**
     * Constructs a {@code Ui} object.
     *
     * @param name Name of the Duke's UI.
     */
    public Ui(String name) {
        this.name = name;
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the introduction line of the UI.
     */
    public void introduce() {
        String line1 = String.format("Hello! I'm %s", this.name);
        String line2 = "What can I do for you?";
        System.out.println(String.format("%s\n%s\n%s\n%s\n", BORDER, line1, line2, BORDER));
    }

    /**
     * Scans for user input.
     */
    public String readInput() {
        return this.sc.nextLine();
    }

    /**
     * Formats the Duke's response in a specific format.
     *
     * @param input Response of the Duke that needs to be formatted.
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
}
