package duke.exception;

/**
 * Representation of invalid keyword in find command exception.
 */
public class InvalidFindException extends DukeException {
    private static final String message = "☹ OOPS!!! The keyword cannot be empty.";
    public InvalidFindException() {
        super(message);
    }
}
