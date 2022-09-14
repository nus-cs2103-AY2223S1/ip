package duke.exceptions;

/**
 * Exception given when user try to alias a non-existant command
 */
public class CommandDoesNotExistException extends DukeException {
    private static final String DESCRIPTION = "%s does not exist!";

    public CommandDoesNotExistException(String name) {
        super(String.format(DESCRIPTION, name));
    }
}
