package seedu.duke.exception;

public class NotEnoughArgumentsException extends DukeException {
    public NotEnoughArgumentsException() {
        super("I'll need more information than that, please, Master.");
    }
}
