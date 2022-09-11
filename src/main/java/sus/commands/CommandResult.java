package sus.commands;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String commandResult;

    /**
     * Constructor.
     *
     * @param commandResult result of command executed
     */
    public CommandResult(String commandResult) {
        this.commandResult = commandResult;
    }

    public String getCommandResult() {
        return commandResult;
    }
}
