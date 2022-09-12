package duke.exception;

public class DukeInvalidException extends DukeException {
    public DukeInvalidException() {
        super("OOPS! I'm sorry, but I don't know what that means." + "\n" + "Please input a valid command.");
    }
}
