package duke;

/**
 * The class that handles exceptions in the Duke program.
 *
 * @author ShummyOwnzYou
 * @version 0.1
 */

public class DukeException extends Exception {

    /**
     * Initializes a DukeException containing the error message to be thrown.
     *
     * @param message The error message to be printed.
     */

    public DukeException(String message) {
        super(message);
    }
}
