package duke;


/**
 * Encapsulates a InvalidDescriptionException.
 */
public class InvalidDescriptionException extends DukeException {

    /**
     * Creates the InvalidDescriptionException object.
     */
    public InvalidDescriptionException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-("
                + "\nCommands should be in the following format:"
                + "\n\tlist"
                + "\n\ttodo (description) /p (level)"
                + "\n\tdeadline (description) /by (dd/MM/yyyy) /p (level)"
                + "\n\tevent (description) /at (time) /p (level)"
                + "\n\tmark (number)"
                + "\n\tunmark (number)"
                + "\n\tdelete (number)");
    }
}
