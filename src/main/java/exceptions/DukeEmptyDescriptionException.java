package exceptions;

public class DukeEmptyDescriptionException extends DukeException{

    public DukeEmptyDescriptionException() {
        super("Sorry. I cannot create add an entry with empty description");
    }
}
