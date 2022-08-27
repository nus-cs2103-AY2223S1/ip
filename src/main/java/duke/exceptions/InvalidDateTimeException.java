package duke.exceptions;

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
