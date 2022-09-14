package duke.exceptions;

/**
 * Exception thrown when the alias already exist in Duke
 */
public class CommandAlreadyExistException extends DukeException {
    private static final String DESCRIPTION = "%s already exist!";

    public CommandAlreadyExistException(String name) {
        super(String.format(DESCRIPTION, name));
    }
}
