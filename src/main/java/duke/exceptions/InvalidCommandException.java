package duke.exceptions;

public class InvalidCommandException extends DukeException {

    /**
     * Initialises InvalidCommandException exception with specified error message.
     *
     * @param errorMessage Message related to error.
     */
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
