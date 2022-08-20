package duke.exception;

public class EmptyCommandException extends DukeException {
    public EmptyCommandException() {
        super("The command cannot be empty");
    }
}
