package exceptions;

public class DukeInvalidDescriptionException extends DukeException {

    public DukeInvalidDescriptionException() {
        super("Description of entry is invalid");
    }
}
