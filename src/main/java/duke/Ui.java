package duke;

import duke.exception.DukeException;

/**
 * A class that deals with interactions with the user.
 */
public class Ui {

    /**
     * Prints the welcome message.
     */
    public void helloMessage() {
        System.out.println("Quack! I'm Duck\nWhat can I do for you?");
    }

    /**
     * Prints a divider.
     */
    public void printDivider() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the exit message.
     */
    public void byeMessage() {
        System.out.println("Quack! Hope to see you again soon!");
    }

    /**
     * Prints the error that is given.
     *
     * @param e The error to print.
     */
    public void printError(DukeException e) {
        System.out.println(e);
    }

    /**
     * Prints the string that is given.
     *
     * @param message The string to print.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
}
