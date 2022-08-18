package duke.exceptions;

public class BlankContentException extends DukeException {
    public BlankContentException() {
        super("CONTENT OF MESSAGE CANNOT BE BLANK");
    }
}
