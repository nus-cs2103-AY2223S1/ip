package duke.exception;

/**
 * Represents a Duke exception to be thrown.
 */
public abstract class DukeException extends Exception {
    /**
     * Constructor for a DukeException.
     * @param message The error message to show the user.
     */
    public DukeException(String message) {
        super("Oops... " + message);
    }
}
