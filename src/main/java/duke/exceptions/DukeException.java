package duke.exceptions;

/**
 * The DukeException wraps up all checked exceptions that occur in the Duke program.
 * This provides a more systematic way to handle exceptions.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
