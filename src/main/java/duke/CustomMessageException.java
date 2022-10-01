package duke;

/**
 * A checked exception that takes in a custom message
 */
public class CustomMessageException extends Exception {
    /**
     * @param message The custom message to take in
     */
    public CustomMessageException(String message) {
        super(message);
    }
}
