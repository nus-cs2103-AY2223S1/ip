package duke.exception;

/**
 * Represent the Exception thrown due to invalid input by User.
 */
public class DukeException extends Exception {
    /**
     * Creates a DukeException instance with the specified error message.
     * @param exceptionMsg The error message.
     */
    public DukeException(String exceptionMsg) {
        super(exceptionMsg);
    }

}
