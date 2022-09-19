package duke.exceptions;

/**
 * Exception given when user try to give alias to or delete a non-existant command.
 */
public class CommandDoesNotExistException extends DukeException {
    private static final String DESCRIPTION =
            "You can't add shortcut to nothing, you can't delete nothing. %s does't exist!";

    public CommandDoesNotExistException(String name) {
        super(String.format(DESCRIPTION, name));
    }
}
