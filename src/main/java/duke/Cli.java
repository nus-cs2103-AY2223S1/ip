package duke;

import java.io.PrintStream;

/**
 * Prints messages for user in the CLI.
 */
public class Cli extends Ui {
    private static final PrintStream OUTPUT = System.out;

    /**
     * Prints 30 "-" that act as a divider.
     */
    @Override
    public void printDivider() {
        OUTPUT.println("------------------------------");
    }

    /**
     * Prints a string within two dividers, and a new line after that.
     * @param s string to be printed within the dividers.
     */
    @Override
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
    @Override
    public void println(String s) {
        OUTPUT.println(s);
    }

    /**
     * Prints the welcome message.
     */
    @Override
    public void showWelcome() {
        this.printWithDivider(super.welcomeMessage);
    }

    /**
     * Prints the message shown upon an error in loading tasks from output file.
     */
    @Override
    public void showLoadingError() {
        this.printWithDivider(super.loadingErrorMessage);
    }
}
