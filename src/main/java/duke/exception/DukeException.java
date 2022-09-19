package duke.exception;

/**
 * An exception thrown by the Duke program.
 */
public class DukeException extends RuntimeException {
    public DukeException(String message) {
        super(message);
    }
}
