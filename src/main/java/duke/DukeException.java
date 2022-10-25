package duke;

/**
 * Custom Exception for Duke
 */
public class DukeException extends Exception {
    /**
     * DukeException Constructor
     * @param message Error message
     */
    public DukeException(String message) {
        super(message);
    }
}