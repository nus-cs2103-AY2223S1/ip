package duke.exception;

/**
 * This class represents a Custom DukeException class for the Duke Chat bot.
 */
public class DukeException extends Exception {
    /**
     * Constructor for the DukeException.
     *
     * @param message Exception message returned.
     */
    public DukeException(String message) {
        super(message);
    }
}
