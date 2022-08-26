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

    /**
     * Terminates the program.
     */
    @Override
    public void run() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
