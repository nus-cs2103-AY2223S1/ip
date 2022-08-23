package duke.exception;

public class EmptyDescriptionException extends IllegalInputException {
    public EmptyDescriptionException() {
        super("OOPS!!! The description of a command cannot be empty.");
    }
}
