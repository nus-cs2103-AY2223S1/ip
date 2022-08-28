package duke.exception;

public class DukeCommandNotFoundException extends DukeException{
    public DukeCommandNotFoundException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
