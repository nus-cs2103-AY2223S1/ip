package duke.exceptions;

/**
 * Represents an exception due to a wrong event format.
 */
public class WrongEventFormatException extends WrongFormatException {
    private static final String message =
            "Wrong format for Event!\\nShould be 'event <description> /at YYYY-MM-DD HHmm'.";

    /**
     * Constructs a wrong event format exception.
     */
    public WrongEventFormatException() {
        super(message);
    }
}
