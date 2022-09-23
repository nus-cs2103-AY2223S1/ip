package duke.exception;

/**
 * The DukeException when user enters an invalid action keyword.
 */
public class InvalidActionException extends CompileException {
    /**
     * Constructs InvalidActionException.
     * @param action The incorrect Action name.
     */
    public InvalidActionException(String action) {
        super("I don't know what [" + action + "] means :-(");
    }
}
