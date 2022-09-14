package duke.exceptions;

/**
 * Representation of an exception where user error when interacting with Duke.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
