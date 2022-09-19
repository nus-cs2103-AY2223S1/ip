package duke.exception;

/**
 * ToDo exception for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class ToDoException extends DukeException {
    /**
     * Constructs a new default ToDoException.
     */
    public ToDoException() {
        super("The description of a todo cannot be empty.");
    }
}
