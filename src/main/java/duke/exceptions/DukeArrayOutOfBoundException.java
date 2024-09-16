package duke.exceptions;

public class DukeArrayOutOfBoundException extends DukeException{

    public DukeArrayOutOfBoundException() {
        super("Index cannot be zero or negative");
    }
}
