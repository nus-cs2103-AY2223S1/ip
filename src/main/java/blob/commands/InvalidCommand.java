package blob.commands;

/**
 * The InvalidCommand class represents an invalid command that has been input by the user
 */
public class InvalidCommand extends Command {

    private final String[] resultMessages;

    /**
     * Returns an InvalidCommand that when executed, returns a CommandResult containing the input
     * messages.
     *
     * @param messages The input messages.
     */
    public InvalidCommand(String ...messages) {
        super(null);
        this.resultMessages = messages;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(resultMessages);
    }
}
