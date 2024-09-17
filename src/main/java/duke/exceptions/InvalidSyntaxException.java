package duke.exceptions;

/**
 * Describes an exception when the user inputs invalid syntax.
 */
public class InvalidSyntaxException extends DukeException {
    private static final String errorHeader = "Invalid Syntax!\n";

    public InvalidSyntaxException(String errorMessage) {
        super(errorHeader + errorMessage);
    }
}
