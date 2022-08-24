public class InvalidCommandException extends Exception{
    private final String commandString;

    public InvalidCommandException(String commandString) {
        super(commandString + " is not a valid command.");
        this.commandString = commandString;
    }

    public String getCommandString() {
        return this.commandString;
    }
}
