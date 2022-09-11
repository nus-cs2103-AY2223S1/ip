package duke.common;

/**
 * A <code>RuntimeException</code> thrown by Duke caused by invalid input.
 *
 * @author Rama Aryasuta Pangestu
 */
public class DukeException extends Exception {
    /**
     * Constructs a new exception which is thrown by Duke with the specified detail message.
     *
     * @param message the detail message
     */
    public DukeException(String message) {
        super(message);
    }
}
