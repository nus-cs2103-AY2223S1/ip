package duke.exceptions;

/**
 * Represents an exception due to a wrong find format.
 */
public class WrongFindFormatException extends WrongFormatException {
    private static final String CORRECT_FIND_SYNTAX = "find <keyword>";

    /**
     * Constructs a wrong find format exception.
     */
    public WrongFindFormatException() {
        super(CORRECT_FIND_SYNTAX);
    }
}
