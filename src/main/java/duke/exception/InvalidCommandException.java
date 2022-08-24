package duke.exception;
public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {}

    public InvalidCommandException(String message) {
        super(message);
    }
}
