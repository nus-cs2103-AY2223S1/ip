package duke;

/**
 * Represents a custom exception type for Duke.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
