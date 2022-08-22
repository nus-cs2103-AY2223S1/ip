package duke.exception;

/**
 * An exception thrown by the Duke program.
 * @author neosunhan
 */
public class DukeException extends RuntimeException {
    public DukeException(String message) {
        super(message);
    }
}
