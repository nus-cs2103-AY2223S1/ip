package Duke;

public class BlankDescriptionException extends DukeException {
    public BlankDescriptionException() {
        super("The description of a command cannot be empty!");
    }

}