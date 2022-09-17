package duke;

public class DukeNoDescriptionException extends DukeException {
    public DukeNoDescriptionException() {
        super("The description of a command cannot be empty!");
    }

}
