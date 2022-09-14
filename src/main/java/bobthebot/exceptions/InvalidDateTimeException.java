package bobthebot.exceptions;

/**
 * Custom exception class for handling exceptions specific to invalid date and time given by user.
 */
public class InvalidDateTimeException extends Exception {
    /**
     * Constructs InvalidDateTimeException.
     */
    public InvalidDateTimeException(String errorMessage) {
        super(errorMessage);
    }
}
