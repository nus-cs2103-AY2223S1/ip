package duke.exceptions;

/**
 * Exception when an invalid format is given when trying to add an alias
 */
public class InvalidAliasFormatException extends DukeException {
    private static final String DESCRIPTION =
            "Invalid format, please use <alias>-><target command>, alias be only alphanumeric";

    public InvalidAliasFormatException() {
        super(DESCRIPTION);
    }
}
