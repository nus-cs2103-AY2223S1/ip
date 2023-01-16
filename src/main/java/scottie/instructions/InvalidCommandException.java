package scottie.instructions;

/**
 * An exception that is thrown when the user enters a command that is
 * not a valid command.
 */
public class InvalidCommandException extends Exception {
    private final String commandName;

    /**
     * Constructs an InvalidCommandException with the given commandName.
     *
     * @param commandName The name of the invalid command.
     */
    InvalidCommandException(String commandName) {
        super(commandName + " is not a valid command.");
        this.commandName = commandName;
    }

    /**
     * Returns the name of the invalid command.
     *
     * @return The name of the invalid command.
     */
    public String getCommandName() {
        return this.commandName;
    }
}
