package scottie.parser;

public class InvalidCommandException extends Exception{
    private final String commandString;

    InvalidCommandException(String commandString) {
        super(commandString + " is not a valid command.");
        this.commandString = commandString;
    }

    public String getCommandString() {
        return this.commandString;
    }
}
