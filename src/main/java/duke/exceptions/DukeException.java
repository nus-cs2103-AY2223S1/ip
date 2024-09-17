package duke.exceptions;

/**
 * Describes how an exception in the Duke program happens.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
