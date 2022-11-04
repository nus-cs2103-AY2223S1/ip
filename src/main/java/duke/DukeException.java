package duke;

/**
 * Encapsulates Exceptions encountered throughout
 * the runtime of Duke.
 * Note that DukeException is a high-level exception and
 * the detail message will be displayed to end users.
 */
public class DukeException extends Exception {

    /**
     * Constructs DukeException with the specified detail message.
     *
     * @param message The detail message
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Constructs DukeException with the specified detail message and cause.
     *
     * @param message The detail message
     * @param cause The cause
     */
    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }

}
