package duke.exceptions;

/**
 * Exception for duke-specific exceptions.
 */
public class DukeException extends Exception {

    /**
     * Constructor for a duke exception.
     * @param message Error message.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns string representation of error.
     *
     * @return String representation of error.
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
