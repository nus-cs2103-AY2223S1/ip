package duke.exception;




/**
 * Creates an exception that is thrown when the user inputs an invalid command.
 */
public class InvalidInputException extends DukeException {
    public InvalidInputException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
