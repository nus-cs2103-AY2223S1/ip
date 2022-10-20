package duke.exception;

/**
 * An exception class that encapsulates the possible error could occur for editing a task.
 */
public class InvalidEditCommandException extends DukeException {
    public InvalidEditCommandException(String errorMessage) {
        super(errorMessage);
    }
}
