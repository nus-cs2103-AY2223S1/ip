package duke.exception;

/**
 * Represents an exception specific to Duke
 */
public class DukeException extends RuntimeException {
    /**
     * Creates a new instance of DukeException.
     *
     * @param errMessage The error message to be displayed.
     */
    public DukeException(String errMessage) {
        super(errMessage);
    }
}
