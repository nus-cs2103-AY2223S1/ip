package duke.exception;

/**
 * DukeException represents an Exception thrown because of an invalid input by the user.
 */
public class DukeException extends Exception {

    /**
     * Create a DukeException with the specified Message.
     *
     * @param message The message to be displayed.
     */
    public DukeException(String message) {
        super(message);
    }
}
