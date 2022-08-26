package duke.exception;

/**
 * InvalidInputException is a RuntimeException that is thrown when an invalid input is provided.
 *
 * @author Eugene Tan
 */
public class InvalidInputException extends Exception {

    /**
     * Constructor for InvalidInputException.
     *
     * @param errorMessage The error message to be shown.
     */
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}
