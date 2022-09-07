package duke.exception;

/**
 * Signals that Duke has encountered an invalid input from the user.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
