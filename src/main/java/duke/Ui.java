package duke;

import java.util.Scanner;

/**
 * A class handling the duke's interactions with the user.
 */
public class Ui {
    private static final String SEPARATOR = "     ____________________________________________________________\n";
    private static final String INDENT = "     ";
    private final Scanner sc;

    /**
     * Constructs an Ui
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the Welcome message.
     */
    public void showWelcome() {
        dukePrint("Oi! I'm Dook",
                "What's up?",
                "Please type your date and time in this format: yyyy-mm-dd");
    }

    /**
     * Prints the exit message
     */
    public void showBye() {
        dukePrint("Bye.", ">:)");
    }

    /**
     * Prints a loading error message.
     */
    public void showLoadingError() {
        dukePrint("Failed to load :(");
    }

    /**
     * Prints an error message
     *
     * @param msg the message of the error.
     */
    public void showError(String msg) {
        dukePrint("Something bad happened! :O", "Error message: " + msg);
    }

<<<<<<< HEAD
    public void showLine() {
        System.out.println(SEPARATOR);
    }

    /**
     * Reads a command from the user input.
     *
     * @return a String containing the user input
     */
=======
>>>>>>> branch-A-CodingStandard
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Closes the Ui.
     */
    public void close() {
        this.sc.close();
    }

    /**
     * Format for printing messages.
     *
     * @param lines messages to be printed
     */
    public static void dukePrint(String... lines) {
        StringBuilder res = new StringBuilder(SEPARATOR);
        for (String line : lines) {
            res.append(INDENT).append(line).append("\n");
        }
        res.append(SEPARATOR);
        System.out.println(res.toString());
    }
}
