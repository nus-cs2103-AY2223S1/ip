package duke;

/**
 * Exception raised in Duke.
 */
public class DukeException extends Exception {

    private static final String ERROR_MESSAGE_PREFIX = "Oh no! ";

    /**
     * Constructor for DukeException.
     *
     * @param message Error message.
     * @param error Error that resulted in this error.
     */
    public DukeException(String message, Throwable error) {
        super(message, error);
    }

    /**
     * Constructor for DukeException.
     *
     * @param message Error message.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return ERROR_MESSAGE_PREFIX + super.getMessage();
    }
}
