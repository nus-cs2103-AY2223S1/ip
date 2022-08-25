package commands;

/**
 * The Command class represents an executable command that can be invoked by the user.
 */
public abstract class Command {
    /** The word used to invoke the command */
    public final String COMMAND_WORD;

    protected Command(String commandWord) {
        this.COMMAND_WORD = commandWord;
    }

    /**
     * Executes the command.
     */
    public abstract CommandResult execute();
}
