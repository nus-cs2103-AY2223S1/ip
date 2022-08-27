package duke.exception;

/**
 * Custom exception for use within the Duke application.
 */
public class DukeException extends Exception {
    /**
     * Creates a DukeException with an error message.
     * 
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
