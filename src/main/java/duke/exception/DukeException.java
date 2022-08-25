package duke.exception;

/**
 * Represents checked exceptions that might occur in Duke application.
 */
public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }
}
