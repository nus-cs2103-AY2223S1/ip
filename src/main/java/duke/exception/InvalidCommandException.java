package duke.exception;

/**
 * An Exception class that encapsulates the situation where the user has given a command that Duke does not recognise.
 */
public class InvalidCommandException extends IllegalInputException {

    /**
     * Constructs an InvalidCommandException.
     */
    public InvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-("
                + "\n"
                + "Try HELP to find out the list of commands.");
    }
}
