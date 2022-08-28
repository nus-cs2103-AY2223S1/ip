package duke;

/**
 * Ui handles user interaction.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String BORDER = LINE + "\n";
    private static final String GREETING = "Hello! I am duke.\n" + "What can I do for you?\n";
    private static final String INVALID = "☹ OOPS!!! ";

    /**
     * Prints a message to the user.
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        System.out.println(BORDER + message + BORDER);
    }

    /**
     * Prints a welcome message to the user.
     */
    public void welcomeMessage() {
        System.out.println(BORDER + GREETING + BORDER);
    }

    /**
     * Prints an error message to the user.
     * @param error The error message to be printed.
     */
    public void invalidMessage(String error) {
        System.out.println(BORDER + INVALID + error + "\n" + BORDER);
    }
}
