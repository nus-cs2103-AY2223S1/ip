package exceptions;

/**
 * Exceptions that happen in Duke, such as parsing.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new DukeException with a particular error message.
     *
     * @param errorMessage Error message string.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
