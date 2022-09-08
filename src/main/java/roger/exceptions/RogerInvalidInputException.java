package roger.exceptions;

/**
 * Thrown when the user gives invalid input.
 */
public class RogerInvalidInputException extends Exception {
    /**
     * Create a RogerInvalidInputException.
     * @param message The message to be shown to the end user.
     */
    public RogerInvalidInputException(String message) {
        super(message);
    }
}
