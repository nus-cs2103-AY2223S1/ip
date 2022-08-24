package duke.exception;

public class EmptyIndexException extends IllegalInputException {
    public EmptyIndexException() {
        super("OOPS!!!I couldn't recognise the index after the command word.");
    }
}
