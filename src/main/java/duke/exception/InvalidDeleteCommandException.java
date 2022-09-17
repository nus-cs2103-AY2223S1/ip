package duke.exception;

public class InvalidDeleteCommandException extends DukeException {
    public InvalidDeleteCommandException(String errorMessage) {
        super(errorMessage);
    }
}
