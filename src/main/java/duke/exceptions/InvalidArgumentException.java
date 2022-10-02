package duke.exceptions;

/**
 * DukeException child exception specifying an invalid argument.
 */
public class InvalidArgumentException extends DukeException {

    /**
     * Initialises InvalidArgumentException exception with specified error message.
     *
     * @param errorMessage Message related to error.
     */
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
