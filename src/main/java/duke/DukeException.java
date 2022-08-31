package duke;

/**
 * Exception for Duke Application.
 *
 * @author Elbert Benedict
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super("OOPS!!! " + message);
    }
}
