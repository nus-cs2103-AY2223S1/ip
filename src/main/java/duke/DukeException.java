package duke;

/**
 * Represents a custom exception thrown by Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     * @param error The error message to display.
     */
    public DukeException(String error) {
        super(error);
    }
}
