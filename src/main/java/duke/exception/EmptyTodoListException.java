package duke.exception;

/**
 * Represents exception when todo command is run without providing description.
 */
public class EmptyTodoListException extends Exception {
    /**
     * Constructs an EmptyTodoListException.
     */
    public EmptyTodoListException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
