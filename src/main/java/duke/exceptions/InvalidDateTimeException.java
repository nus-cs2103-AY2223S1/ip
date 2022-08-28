package duke.exceptions;

/**
 * DukeException child exception specifying error related to datetime.
 */
public class InvalidDateTimeException extends DukeException {

    /**
     * Initialises InvalidDateTimeException exception with specified error message.
     *
     * @param errorMessage Message related to error.
     */
    public InvalidDateTimeException(String errorMessage) {
        super(errorMessage);
    }
}
