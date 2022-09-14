package duke;

import duke.exception.DukeException;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "_______________________________________________________________";
    private static final String WELCOME_MSG = "Hello! I'm Duke.\nWhat can I do for you?";
    private static final String OOPS_MSG = "☹ OOPS! ";

    /**
     * Prints a message to the user.
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        System.out.println(Ui.HORIZONTAL_LINE + "\n" + message + "\n" + Ui.HORIZONTAL_LINE);
    }

    /**
     * Greets the user with a custom welcome message.
     */
    public void greet() {
        System.out.println(Ui.HORIZONTAL_LINE + "\n" + Ui.WELCOME_MSG + "\n" + Ui.HORIZONTAL_LINE);
    }

    /**
     * Prints the message of a DukeException with formatting.
     * @param exception A DukeException.
     */
    public void printExceptionMessage(DukeException exception) {
        System.out.println(Ui.HORIZONTAL_LINE + "\n"
                + Ui.OOPS_MSG + exception.getMessage()
                + Ui.OOPS_MSG + exception.getMessage()
                + "\n" + Ui.HORIZONTAL_LINE);
    }

}


