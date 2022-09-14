package duke;

/**
 * Encapsulates a Duke Exception for leaving the description of a todo empty.
 *
 */
public class EmptyTodoException extends DukeException {
    public EmptyTodoException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
