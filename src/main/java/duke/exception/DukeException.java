package duke.exception;

/**
 * Represents an error message to be displayed to the user.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
