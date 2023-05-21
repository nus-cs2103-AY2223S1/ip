package duke.exceptions;

/**
 * Represents an exception due to a wrong event format.
 */
public class WrongEventFormatException extends WrongFormatException {
    private static final String CORRECT_EVENT_SYNTAX = "event <description> /at YYYY-MM-DD HHmm";

    /**
     * Constructs a wrong event format exception.
     */
    public WrongEventFormatException() {
        super(CORRECT_EVENT_SYNTAX);
    }
}
