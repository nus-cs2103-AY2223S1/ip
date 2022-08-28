package duke;

public class InvalidDescriptionException extends DukeException {
    public InvalidDescriptionException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}