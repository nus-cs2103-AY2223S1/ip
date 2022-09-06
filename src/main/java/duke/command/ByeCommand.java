package duke.command;

/**
 * ByeCommand is a command for Duke to terminate.
 *
 * @author totsukatomofumi
 */
public class ByeCommand extends Command {
    /**
     * Constructor for the command.
     */
    public ByeCommand() {
        super();
    }

    @Override
    public String get() {
        return "Bye. Hope to see you again soon!";
    }
}
