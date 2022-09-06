package duke.exceptions;

public class BlankDescriptionException extends DukeException {
    public BlankDescriptionException() {
        super("DESCRIPTION OF TASK CANNOT BE BLANK");
    }
}
