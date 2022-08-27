package duke.exception;

/**
 * Represents an exception for the Duke application.
 *
 * @author njxue
 * @version v0.1
 */
public class DukeException extends Exception {
    /**
     * Returns a DukeException containing the message describing the exception.
     *
     * @param message Message describing the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
