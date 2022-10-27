package duke;

/**
 * Encapsulates an exception that is thrown in the Duke program
 */
public class DukeException extends Exception {

    /**
     * Creates the DukeException object.
     *
     * @param message String representation of the error.
     */
    public DukeException(String message) {
        super(message);
    }
}
