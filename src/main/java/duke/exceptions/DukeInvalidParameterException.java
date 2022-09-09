package duke.exceptions;

public class DukeInvalidParameterException extends DukeException {

    public DukeInvalidParameterException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Invalid parameters: " + super.getMessage();
    }
}
