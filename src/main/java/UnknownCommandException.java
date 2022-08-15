public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String invalidCommand) {
        super(invalidCommand + " is not a valid command!");
    }
}