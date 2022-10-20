package duke.exception;

/**
 * An exception class that encapsulates the possible error could occur for adding a todo task.
 */
public class InvalidTodoTaskException extends DukeException {
    public InvalidTodoTaskException(String errorMessage) {
        super(errorMessage);
    }
}
