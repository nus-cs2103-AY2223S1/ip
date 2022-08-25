package duke.commands;

/**
 * Represents an exit command in the Duke application.
 */
public class ExitCommand extends Command {
    /** Command word of the exit command. */
    public static final String COMMAND_WORD = "bye";

    /**
     * {@inheritDoc}
     */
    public CommandResult execute() {
        return new CommandResult("", false, true);
    }
}
