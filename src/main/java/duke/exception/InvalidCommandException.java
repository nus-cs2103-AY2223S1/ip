package duke.exception;

/**
 * InvalidCommandException is a RuntimeException that is thrown when an invalid command is provided.
 *
 * @author Eugene Tan
 */
public class InvalidCommandException extends Exception {

    /**
     * Constructor for InvalidCommandException.
     *
     * @param errorMessage The error message to be shown.
     */
        public InvalidCommandException(String errorMessage) {
            super(errorMessage);
        }
}
