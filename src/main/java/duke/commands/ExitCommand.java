package duke.commands;

/**
 * Represents an exit command in the Duke application.
 */
public class ExitCommand extends Command {
    /** Command word of the exit command. */
    public static final String COMMAND_WORD = "bye";

    public CommandResult execute() {
        return new CommandResult("", CommandResult.Action.EXIT);
    }
}
