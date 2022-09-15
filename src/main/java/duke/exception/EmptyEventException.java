package duke.exception;

/**
 * A class representing the empety event description exception.
 */
public class EmptyEventException extends DukeException {
    public EmptyEventException() {
        super("The description of an event cannot be empty.");
    }
}
