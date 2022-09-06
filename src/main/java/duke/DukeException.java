package duke;

/**
 * Thrown by Duke to indicate Duke-related errors.
 */
public class DukeException extends Exception {
    /**
     * Creates a DukeException with a message.
     *
     * @param message the message
     */
    public DukeException(String message) {
        super(message);
    }
}
