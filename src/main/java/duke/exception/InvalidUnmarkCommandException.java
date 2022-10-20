package duke.exception;

/**
 * An exception class that encapsulates the possible error could occur for unmark a task.
 */
public class InvalidUnmarkCommandException extends DukeException {
    public InvalidUnmarkCommandException(String errorMessage) {
        super(errorMessage);
    }
}
