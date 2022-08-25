package commands;

/**
 * The CommandResult Class represents the result that derives from executing
 * a command in the application
 */
public class CommandResult {
    /** The resulting messages that are derived from executing a previous command */
    private final String[] resultMessages;

    public CommandResult(String ...messages) {
        this.resultMessages = messages;
    }

    /**
     * Returns the command result messages.
     *
     * @return The command result messages
     */
    public String[] getResultMessages() {
        return resultMessages;
    }
}
