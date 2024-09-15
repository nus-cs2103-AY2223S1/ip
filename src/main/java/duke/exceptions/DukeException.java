package duke.exceptions;

/**
 * A subclass of class Exception that indicates conditions
 * that the Duke application might want to catch.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
