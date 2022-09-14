package duke.utils;

/**
 * The UI class abstracts out how the messages are delivered to the user.
 */
public class Ui {
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static String makeLine() {
        return "-".repeat(50) + "\n";
    }

    /**
     * Wraps the message within two lines.
     * @param message the message to be shown to the user.
     */
    public static String wrapWithLines(String message) {
        return makeLine() + message + makeLine();
    }

    /**
     * Prints the custom error message.
     * @param message the error message to be printed.
     * @return the custom error message.
     */
    public static String printErrorMessage(String message) {
        return makeLine() + "OOPS!" + message + makeLine();
    }
}
