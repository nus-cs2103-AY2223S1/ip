public class InvalidCommandFormatException extends DukeException {
    public InvalidCommandFormatException(String format) {
        super(String.format("Invalid command format.\nUsage: %s", format));
    }
}
