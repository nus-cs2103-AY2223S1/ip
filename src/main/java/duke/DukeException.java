package duke;

/**
 * Defines `DukeException` class.
 * `DukeException` is a checked exception thrown for exceptions
 * encountered while executing the `Duke` program.
 */
public class DukeException extends Exception {
    /**
     * Constructor for `DukeException`.
     * @param message Error message given by thrower of this exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Constructor for `DukeException` when `Throwable` cause is supplied.
     * @param message Error message given by thrower of this exception.
     * @param cause   `Throwable` cause that caused this exception to be
     *                thrown.
     */
    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }
}
