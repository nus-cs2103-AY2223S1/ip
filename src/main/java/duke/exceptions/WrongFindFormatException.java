package duke.exceptions;

/**
 * Represents an exception due to a wrong find format.
 */
public class WrongFindFormatException extends WrongFormatException {
    private static final String message =
            "Wrong format for Find!\\nShould be 'find <keyword>'.";

    /**
     * Constructor for an invalid index exception.
     */
    public WrongFindFormatException() {
        super(message);
    }
}
