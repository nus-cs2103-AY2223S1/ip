package duke.exception;

/**
 * An exception class that encapsulates the possible error could occur for deleting a task.
 */
public class InvalidDeleteCommandException extends DukeException {
    public InvalidDeleteCommandException(String errorMessage) {
        super(errorMessage);
    }
}
