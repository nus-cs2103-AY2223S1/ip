package duke.exceptions;

/**
 * Representation of an exception where user attempts to create
 * an invalid task type
 */
public class InvalidTaskException extends DukeException {
    public InvalidTaskException(String message) {
        super(message + " IS NOT A VALID TASK");
    }
}
