package duke.exception;

/**
 * A class representing the empty todo description exception.
 */
public class EmptyTodoException extends DukeException {
    public EmptyTodoException() {
        super("The description of a todo cannot be empty.");
    }
}
