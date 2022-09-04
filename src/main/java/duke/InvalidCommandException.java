package duke;

/**
 * InvalidCommandException is an exception that is thrown when user enter invalid command.
 */
public class InvalidCommandException extends DukeException {

    public InvalidCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
