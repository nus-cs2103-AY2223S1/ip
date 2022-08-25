package duke.exception;

/**
 * The EmptyTodoException class represents a DukeException that is thrown when
 * the user inputs a Todo without any description.
 *
 * @author Edric Yeo
 */
public class EmptyTodoException extends DukeException {

    /**
     * Constructor for a EmptyTodoException.
     */
    public EmptyTodoException() {
        super("The description of a todo cannot be empty");
    }

}
