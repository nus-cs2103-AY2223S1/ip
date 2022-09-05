package duke.exceptions;

/**
 * Represents an exception due to no undo actions.
 */
public class NoUndoActionsException extends DukeException {
    private static final String message = "No actions to undo!";

    /**
     * Constructor for a bad save data exception.
     */
    public NoUndoActionsException() {
        super(message);
    }
}
