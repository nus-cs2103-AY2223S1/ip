package duke.exceptions;

/**
 * Exception when an invalid format is given when trying to add an alias
 */
public class InvalidAliasFormatException extends DukeException {
    private static final String DESCRIPTION =
            "Huh?? please use <alias>-><target command>, alias must be alphanumeric and start with a letter";

    public InvalidAliasFormatException() {
        super(DESCRIPTION);
    }
}
