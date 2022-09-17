package duke.exceptions;

/**
 * Thrown when the description of the task is empty.
 */
public class EmptyDescriptionException extends DukeException {
    private static final String DESCRIPTION = "What's this task even about?? Description of %s cannot be empty";

    public EmptyDescriptionException(String commandName) {
        super(String.format(DESCRIPTION, commandName));
    }
}
