package duke.exception;

/**
 * A class representing an empty deadline description exception.
 */
public class EmptyDeadlineException extends DukeException {
    public EmptyDeadlineException() {
        super("The description of a deadline cannot be empty.");
    }
}
