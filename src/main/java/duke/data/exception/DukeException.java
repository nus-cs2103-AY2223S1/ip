package duke.data.exception;

/**
 * Represents an exception to be thrown when invalid inputs are provided by the user
 * or when there is an error loading or saving the list of tasks.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super("Oops... " + message);
    }
}
