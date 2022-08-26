package duke.exception;

/**
 * Represents an error during parsing of input from the user.
 */
public class DukeException extends Exception {
    /**
     * Constructor for an error for Duke.
     */
    public DukeException() {
        super();
    }

    @Override
    public String toString() {
        return "( • ᴖ • ｡) Error encountered: ";
    }
}
