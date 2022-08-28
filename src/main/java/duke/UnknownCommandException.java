package duke;

/**
 * DukeException for when an unknown command is given.
 *
 */
public class UnknownCommandException extends DukeException {
    /**
     * Constructor.
     */
    public UnknownCommandException() {
        super("OOPS!!! I'm sorry but I have no idea what that means :'(");
    }
}
