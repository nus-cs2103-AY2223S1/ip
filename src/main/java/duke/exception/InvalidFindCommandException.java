package duke.exception;

/**
 * An exception class that encapsulates the possible error could occur for finding a task.
 */
public class InvalidFindCommandException extends DukeException {
    public InvalidFindCommandException(String errorMessage) {
        super(errorMessage);
    }
}
