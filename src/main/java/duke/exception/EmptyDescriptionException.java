package duke.exception;

/**
 * Empty description exception.
 */
public class EmptyDescriptionException extends  DukeException{

    /**
     * Constructor for empty description exception.
     */
    public EmptyDescriptionException(){
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
