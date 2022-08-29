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
                + "\n\ttodo (description)"
                + "\n\tdeadline (description) /by (dd/MM/yyyy)"
                + "\n\tevent (description) /at (time)"
                + "\n\tmark (number)"
                + "\n\tunmark (number)"
                + "\n\tdelete (number)");
    }
}