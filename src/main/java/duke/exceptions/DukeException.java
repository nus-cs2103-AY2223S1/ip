package duke.exceptions;

/**
 * Represents an exception in the Duke application.
 */
public abstract class DukeException extends Exception {
    /**
     * Constructs a Duke Exception with a message.
     *
     * @param message Message for the Duke Exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
