package duke.commands;

/**
 * Represents an exit command in the Duke application.
 */
public class ExitCommand extends Command {
    /** Command word of the exit command. */
    public static final String COMMAND_WORD = "bye";
    public static final String EXIT_MESSAGE = "Bye! *sad beep*\n"
            + "Hope to see you soon!";

    public CommandResult execute() {
        return new CommandResult(EXIT_MESSAGE, CommandResult.Action.EXIT);
    }
}
