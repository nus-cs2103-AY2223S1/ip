package duke.command;

/**
 * This class encapsulates an exit command from the user.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Returns the exit message.
     *
     * @return The exit message.
     */
    @Override
    public String execute() {
        return "Bye! Hope to see you again.";
    }
}
