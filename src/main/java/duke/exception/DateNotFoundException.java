package duke.exception;

/**
 * Customised Exception for missing date input.
 */
public class DateNotFoundException extends Exception {

    /**
     * Creates a DateNotFoundException.
     * @param message User input with missing date.
     */
    public DateNotFoundException(String message) {
        super("Date cannot be found in: " + message);
    }
}
