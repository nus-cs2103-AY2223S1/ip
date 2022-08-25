package duke.exception;

/**
 * Child exception which caters to Command cannot be recognised by Duke.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {}

    public InvalidCommandException(String message) {
        super(message);
    }
}
