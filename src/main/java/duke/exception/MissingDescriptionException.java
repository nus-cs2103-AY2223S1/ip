package duke.exception;

public class MissingDescriptionException extends DukeException {

    public MissingDescriptionException(String task) {
        super("The description of a " + task + " cannot be empty!");
    }
}
