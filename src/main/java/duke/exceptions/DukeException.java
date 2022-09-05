package duke.exceptions;

/**
 * Represents an exception in the Duke application.
 */
public abstract class DukeException extends Exception {
    /**
     * Constructor for a Duke Exception that takes in a message.
     *
     * @param message Message for the Duke Exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
