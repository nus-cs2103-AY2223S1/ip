package duke.exception;

public class InvalidCommandException extends IllegalInputException {
    public InvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-("
                + "\n" + "   " + "Try HELP to find out the list of commands.");
    }
}
