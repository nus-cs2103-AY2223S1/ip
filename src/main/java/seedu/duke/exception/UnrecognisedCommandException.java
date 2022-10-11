package seedu.duke.exception;

/**
 * Exception class to be used when user input cannot be handled
 */
public class UnrecognisedCommandException extends DukeException {
    public UnrecognisedCommandException() {
        super("I'm sorry Master, I have not yet been trained to respond to that.");
    }
}
