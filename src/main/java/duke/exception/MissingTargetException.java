package duke.exception;

public class MissingTargetException extends DukeException {

    public MissingTargetException(String command) {
        super("OOPS!!! Enter a valid integer task number to " + command);
    }
}
