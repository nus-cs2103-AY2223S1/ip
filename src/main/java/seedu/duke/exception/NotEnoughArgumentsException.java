package seedu.duke.exception;

/**
 * Exception class to be used when insufficient arguments are inputed
 */
public class NotEnoughArgumentsException extends DukeException {
    public NotEnoughArgumentsException() {
        super("I'll need more information than that, please, Master.");
    }
}
