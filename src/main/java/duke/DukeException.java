package duke;

/**
 * A typical exception thrown by the Duke application.
 */
public class DukeException extends RuntimeException {
    public DukeException(String message) {
        super(message);
    }
}
