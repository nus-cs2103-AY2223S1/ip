package duke.exceptions;

public class BlankContentException extends DukeException {
    public BlankContentException(String message) {
        super("CONTENT OF MESSAGE CANNOT BE BLANK WHEN YOU ARE\n" + message);
    }
}
