package duke.exception;

/**
 * Represents the Exception thrown due to invalid user inputs.
 */
public class DukeException extends RuntimeException {

    public static final String OOPS = "☹ OOPS!!! ";

    /**
     * Constructs a new DukeException.
     *
     * @param message Message of the exception.
     */
    public DukeException(String message) {
        super(OOPS + message);
    }
}
