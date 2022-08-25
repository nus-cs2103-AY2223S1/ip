package duke;

/**
 * Represents an exception specific to the Duke program.
 */
public class DukeException extends Exception {
    /**
     * Returns an instance of DukeException.
     * @param msg Error message.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
