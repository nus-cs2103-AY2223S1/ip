package john.commands;

/**
 * Represents an exit command.
 * Terminates the program.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Returns true if the command is an instance of ByeCommand, false otherwise.
     * @param command The command to check if it is ByeCommand.
     * @return True if command is an instance of ByeCommand, false otherwise.
     */
    public static boolean isBye(Command command) {
        return command instanceof ByeCommand;
    }

    /**
     * Returns an empty string.
     * @return An empty string.
     */
    @Override
    public String execute() {
        return "";
    }
}
