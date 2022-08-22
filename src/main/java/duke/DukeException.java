package duke;

/**
 * Class for handling exceptions of Duke.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class DukeException extends Exception {
    /**
     * Creates a DukeException with specified error message.
     *
     * @param message The error message for the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
