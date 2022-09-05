package duke.exception;

/**
 * General Exception for the Duke bot.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     * @param error Error message for the exception
     */
    public DukeException (String error) {
        super(error);
    }
}
