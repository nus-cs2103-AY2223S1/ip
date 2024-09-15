package duke.exception;

public class MissingDescriptionException extends DukeException{
    private static final String errorString = "Description of command is required.";

    public MissingDescriptionException() {
        super(errorString);
    }
}
