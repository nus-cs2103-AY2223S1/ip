package Duke;

public class DukeEmptyDescriptionException extends DukeException{

    public DukeEmptyDescriptionException() {
        super("Oops, the description of a command cannot be empty!");
    }
}
