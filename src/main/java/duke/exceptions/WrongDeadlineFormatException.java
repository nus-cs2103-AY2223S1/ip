package duke.exceptions;

/**
 * Represents an exception due to a wrong deadline format.
 */
public class WrongDeadlineFormatException extends WrongFormatException {
    private static final String CORRECT_DEADLINE_SYNTAX = "deadline <description> /by YYYY-MM-DD HHmm";

    /**
     * Constructs a wrong deadline format exception.
     */
    public WrongDeadlineFormatException() {
        super(CORRECT_DEADLINE_SYNTAX);
    }
}
