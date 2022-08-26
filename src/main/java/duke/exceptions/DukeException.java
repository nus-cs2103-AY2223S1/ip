package duke.exceptions;

/**
 * Represents an Exception due to Duke and encapsulates an exception message.
 */
public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }
}
