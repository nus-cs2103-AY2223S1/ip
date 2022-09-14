package seedu.duke.exception;

public class NotNumberException extends DukeException {
    public NotNumberException() {
        super("Please input the index of the relevant task after the command word, Master.");
    }
}
