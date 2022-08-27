package duke.exceptions;

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