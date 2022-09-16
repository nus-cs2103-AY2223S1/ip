package duke.exception;

/**
 * A DukeException is the superclass of all exceptions that are thrown during the execution of the application.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
