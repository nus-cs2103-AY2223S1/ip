package duke.exception;

/**
 * A class that encapsulates the exceptions caused by an unknown command
 * by the user from the Duke program.
 */
public class UnknownCommand extends DukeException {
    /**
     * The class constructor.
     */
    public UnknownCommand() {
        super("Unknown command!");
    }
}
