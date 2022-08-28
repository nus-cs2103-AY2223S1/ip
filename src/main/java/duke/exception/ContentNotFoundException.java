package duke.exception;

/**
 * Customised class for missing content after command.
 */
public class ContentNotFoundException extends Exception {

    /**
     * Creates a ContentNotFoundException.
     * @param message User input that has missing content.
     */
    public ContentNotFoundException(String message) {

        super(message);
    }
}
