package duke.command;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    private static final String GOOD_BYE_MSG = "Bye. Hope to see you again soon!";

    /**
     * Returns the goodbye message.
     * @return The message to be displayed upon the execution of the command.
     */
    @Override
    public String execute() {
        return ExitCommand.GOOD_BYE_MSG;
    }
}
