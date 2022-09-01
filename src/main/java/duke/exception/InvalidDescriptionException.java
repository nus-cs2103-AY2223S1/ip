package duke.exception;

/**
 * Represents an exception where the user enters an invalid description.
 */
public class InvalidDescriptionException extends Exception {
    public InvalidDescriptionException(String message) {
        super(message);
    }
}
