package duke;

/**
 * Returns an error that occurs within the application.
 * @author Jason
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
