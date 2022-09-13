package duke.exception;

public class MissingTargetException extends DukeException {

    public MissingTargetException(String command) {
        super("Hmm, I can't find this hole number, enter a valid integer number to " + command);
    }
}
