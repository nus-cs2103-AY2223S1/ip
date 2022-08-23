package duke.exception;

public class InvalidIndexException extends IllegalInputException {
    public InvalidIndexException() {
        super("OOPS!!! The index is illegal.");
    }
}
