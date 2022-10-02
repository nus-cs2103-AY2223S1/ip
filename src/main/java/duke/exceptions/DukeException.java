package duke.exceptions;

/**
 * Generic runtime exception.
 */
public class DukeException extends RuntimeException {

    /**
     * Initialises DukeException exception with specified error message.
     *
     * @param errorMessage Message related to error.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
