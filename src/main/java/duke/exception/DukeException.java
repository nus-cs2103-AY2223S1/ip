package duke.exception;

/**
 * Represents a Custom DukeException class for the Duke Chat bot.
 */
public class DukeException extends Exception {
    /**
     * Constructor for a DukeException.
     *
     * @param message Exception message returned.
     */
    public DukeException(String message) {
        super(message);
    }
}
