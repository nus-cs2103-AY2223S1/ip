package duke.exception;

public class InvalidCommandException extends IllegalInputException {
    public InvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
