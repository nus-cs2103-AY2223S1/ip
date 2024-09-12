package candice.exception;

/**
 * Abstraction for exceptions that are thrown when "undo" has been inputted when there are no commands to undo.
 */
public class InvalidUndoException extends Exception {
    /**
     * Constructor for an exception thrown when an undo command has been inputted when there are no commands to undo.
     */
    public InvalidUndoException() {
        super("Invalid undo my dude. There are no commands to undo");
    }
}
