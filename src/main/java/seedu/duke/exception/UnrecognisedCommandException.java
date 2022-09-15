package seedu.duke.exception;

public class UnrecognisedCommandException extends DukeException {
    public UnrecognisedCommandException() {
        super("I'm sorry Master, I have not yet been trained to respond to that.");
    }
}
