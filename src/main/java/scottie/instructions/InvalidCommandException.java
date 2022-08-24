package scottie.instructions;

public class InvalidCommandException extends Exception {
    private final String commandName;

    InvalidCommandException(String commandName) {
        super(commandName + " is not a valid command.");
        this.commandName = commandName;
    }

    public String getCommandName() {
        return this.commandName;
    }
}
