package duke.exceptions;

/**
 * {@code DukeException} is the superclass of those
 * exceptions that can be thrown during the operation of the Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs a Duke exception.
     *
     * @param message the message to be displayed to the users in the event of the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
