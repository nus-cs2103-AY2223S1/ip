package duke.exception;

/**
 * Exception class for Duke.
 */
public class DukeException extends RuntimeException {
    public DukeException(String s) {
        super(s);
    }

    public DukeException(String s, Throwable cause) {
        super(s, cause);
    }
}
