package duke;

/**
 * Defines <Code>DukeException</Code> class.
 * <p>
 * <Code>DukeException</Code> is a checked exception thrown for exceptions
 * encountered while executing the <Code>Duke</Code> program.
 * </p>
 */
public class DukeException extends Exception {
    /**
     * Constructor for <Code>DukeException</Code>.
     * @param message Error message given by thrower of this exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Constructor for <Code>DukeException</Code> when <Code>Throwable</Code>
     * cause is supplied.
     * @param message Error message given by thrower of this exception.
     * @param cause   <Code>Throwable</Code> cause that caused this exception
     *                to be thrown.
     */
    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }
}
