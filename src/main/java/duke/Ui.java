package duke;

import java.io.PrintStream;

/**
 * Prints messages for user.
 */
public class Ui {
    private final PrintStream OUTPUT = System.out;

    /**
     * Prints 30 "-" that act as a divider.
     */
    public void printDivider() {
        OUTPUT.println("------------------------------");
    }

    /**
     * Prints a string within two dividers, and a new line after that.
     * @param s string to be printed within the dividers.
     */
    public void printWithDivider(String s) {
        this.printDivider();
        OUTPUT.printf("%s\n", s);
        this.printDivider();
        OUTPUT.print("\n");
    }

    /**
     * Prints the string.
     * @param s string.
     */
    public void println(String s) {
        OUTPUT.println(s);
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        this.printWithDivider("Hello! I'm duke.Duke\nWhat can I do for you?");
    }

    /**
     * Prints the message shown upon an error in loading tasks from output file.
     */
    public void showLoadingError() {
        this.printWithDivider(
                "There was a problem loading the tasks from the output file. Starting with empty list.");
    }
}
