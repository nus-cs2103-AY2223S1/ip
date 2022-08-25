package duke;

/**
 * An Exception class to handle all exceptions in this package.
 */
public class DukeException extends Exception {
    /**
     * Creates a new DukeException.
     * @param msg Error message. May be a formatted String.
     * @param args Addition arguments for the formatted error message.
     */
    public DukeException(String msg, Object... args) {
        super(String.format(msg, args));
    }
}
