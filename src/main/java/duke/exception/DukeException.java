package duke.exception;

/**
 * Representation of an exception in Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     * @param message Error message.
     */
    public DukeException(String message) {
        super(message + "\n" + "==> Type 'help' to see available commands.");
    }
}
