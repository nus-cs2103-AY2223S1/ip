package duke.exception;

/**
 * Throws an exception when an invalid command in given.
 */
public class DukeCommandNotFoundException extends DukeException {
    /**
     * Throws an error message when command is not understood.
     */
    public DukeCommandNotFoundException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
