package duke.exceptions;

/**
 * Exception for bad format in user input.
 */
public class DukeBadFormatException extends DukeException {

    /**
     * Constructs a new DukeBadFormatException.
     *
     * @param expectedFormat Expected format for the user.
     */
    public DukeBadFormatException(String expectedFormat) {
        super(String.format("Expected Format: %s", expectedFormat));
    }
}
