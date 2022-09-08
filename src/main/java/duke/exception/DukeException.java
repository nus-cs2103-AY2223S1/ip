package duke.exception;

/**
 * Parent Exception which inherited by different self-defined Exceptions.
 */
public class DukeException extends Exception {
    public DukeException() {}

    public DukeException(String message) {
        super(message);
    }

    public String getMessage() {
        return super.getMessage();
    }
}
