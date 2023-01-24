package seedu.duke.exception;

/**
 * Exception class to be used when format for certain commands is wrong
 */
public class NotNumberException extends DukeException {
    public NotNumberException() {
        super("Please input the index of the relevant task after the command word, Master.");
    }
}
