package duke.exception;

/**
 * Exception when user inputs something invalid to Duke.
 *
 * @author Elgin
 */
public class DukeException extends IllegalArgumentException {
    /** The error message */
    private final String message;

    /**
     * Constructor of DukeException.
     *
     * @param message The error message to be displayed to user.
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns error message, concatenated with oops.
     *
     * @return Error message.
     */
    @Override
    public String getMessage() {
        return this.message;
    }
}
