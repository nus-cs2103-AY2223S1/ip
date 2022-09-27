package duke.exception;

/**
 * The Exceptions might be thrown and caught by Duke.
 */
public class DukeException extends RuntimeException {
    /**
     * Constructs Duke Exception.
     * @param message
     */
    public DukeException(String message) {
        super("OOPS!!! " + message);
    }
}
