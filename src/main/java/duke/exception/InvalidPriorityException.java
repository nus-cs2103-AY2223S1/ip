package duke.exception;

/**
 * a class to represent an invalid priority exception.
 */
public class InvalidPriorityException extends DukeException {
    public InvalidPriorityException() {
        super("Please specify priority as either high, medium, low, or none");
    }
}
