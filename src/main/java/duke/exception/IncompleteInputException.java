package duke.exception;

/**
 * IncompleteInputException is a RuntimeException that is thrown when an incomplete input is provided.
 *
 * @author Eugene Tan
 */
public class IncompleteInputException extends Exception {

    /**
     * Constructor for IncompleteInputException.
     *
     * @param errorMessage The error message to be shown.
     */
    public IncompleteInputException(String errorMessage) {
        super(errorMessage);
    }
}
