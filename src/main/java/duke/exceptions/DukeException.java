package duke.exceptions;

/**
 * Custom Exception for user-related errors
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
