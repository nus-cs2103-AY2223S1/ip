public class InvalidArgumentException extends DukeException {
    public InvalidArgumentException(Command command, String argument) {
        super("Invalid command-line argument for the [" + command + "] command: " + argument + "\nUsage: " + command.getFormat());
    }
}