package duke.exception;

/**
 * Represents the Exception thrown due to invalid input by User.
 */
public class DukeException extends Exception {

    private static final String INVALID = "Oops!!! ";

    /**
     * Creates a DukeException instance with the specified error message.
     * @param exceptionMsg The error message.
     */
    public DukeException(String exceptionMsg) {
        super(DukeException.INVALID + exceptionMsg);
    }

}
