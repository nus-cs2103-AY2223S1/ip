package duke.exceptions;

/**
 * Wraps all checked exception thrown in the Duke program.
 * Systematic representation of exceptions unique to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with specified message.
     *
     * @param message Description of the exception thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}
