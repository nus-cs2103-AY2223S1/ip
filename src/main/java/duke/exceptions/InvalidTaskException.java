package duke.exceptions;

/**
 * DukeException child exception specifying an invalid task being provided.
 */
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
