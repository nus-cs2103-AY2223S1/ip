package blob.commands;

/**
 * The Command class represents an executable command that can be invoked by the user.
 */
public abstract class Command {

    /** The word used to invoke the command */
    public final String commandWord;

    protected Command(String commandWord) {
        this.commandWord = commandWord;
    }

    /**
     * Executes the command.
     *
     * @return A CommandResult object containing the application's response to the invoked command.
     */
    public abstract CommandResult execute();

    /**
     * Returns true if command is the 'bye' command.
     *
     * @return True if command is the 'bye' command
     */
    public boolean isByeCommand() {
        return this instanceof ByeCommand;
    }

    /**
     * Returns true if task is a task command.
     *
     * @return True if task is a task command
     */
    public boolean isTaskCommand() {
        return this instanceof TaskCommand;
    }
}
