package duke.exception;

/**
 * An Exception class that encapsulates the situation where the user has given an invalid index to a task.
 */
public class InvalidIndexException extends IllegalInputException {
    public InvalidIndexException() {
        super("OOPS!!! The index is illegal.");
    }
}
