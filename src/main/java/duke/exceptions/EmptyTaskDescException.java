package duke.exceptions;

/**
 * This exception indicates a missing task description.
 */
public class EmptyTaskDescException extends Exception {

    public EmptyTaskDescException() {
        super("The description of a todo cannot be empty.");
    }

}
