package john.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * The text UI of the program.
 */
public class Ui {
    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructor for Ui.
     * @param in The input for user commands.
     * @param out The output of user commands.
     */
    private Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Outputs a message to users.
     * @param message The message to output.
     */
    public void showToUser(String... message) {
        for (String msg : message) {
            out.print(msg);
        }
    }

    /**
     * Outputs a message to user, styled with an indent.
     * @param message The message to output.
     */
    public void showToUserWithIndent(String... message) {
        for (String msg : message) {
            out.printf("|  %s%n", msg);
        }
    }

    /**
     * Reads and returns the user input.
     * @return User input.
     */
    public String getUserCommand() {
        showToUser("\njduke> ");
        return in.nextLine().trim();
    }

    /**
     * Displays text to greet users upon start of program.
     */
    public void showGreeting() {
        showToUserWithIndent("Welcome to JDuke -- Version 1.0", "What can I do for you?");
    }

    /**
     * Displays text to greet users upon exit of program.
     */
    public void showGoodbye() {
        showToUserWithIndent("Goodbye");
    }

    /**
     * Displays an error message to users if error occurs.
     * @param error The error message to display.
     */
    public void showErrorMessage(String error) {
        showToUserWithIndent("Error:", error);
    }
}
