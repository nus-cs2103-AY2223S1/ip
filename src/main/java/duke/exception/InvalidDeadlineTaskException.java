package duke.exception;

/**
 * An exception class that encapsulates the possible error could occur for adding a deadline task.
 */
public class InvalidDeadlineTaskException extends DukeException {
    public InvalidDeadlineTaskException(String errorMessage) {
        super(errorMessage);
    }
}
