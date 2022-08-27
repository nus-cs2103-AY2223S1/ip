package duke.exceptions;

public class InvalidTaskException extends DukeException {

    /**
     * Initialises InvalidTaskException exception with specified error message.
     *
     * @param errorMessage Message related to error.
     */
    public InvalidTaskException(String errorMessage) {
        super(errorMessage);
    }
}
