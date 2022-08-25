package duke.exception;

/**
 * An Exception class that encapsulates the situation where the user did not specific the index of task.
 */
public class EmptyIndexException extends IllegalInputException {
    public EmptyIndexException() {
        super("OOPS!!!You have not indicate which task you would like to perform action on.");
    }
}
