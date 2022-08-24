package duke.exception;

public class EmptyDateException extends IllegalInputException {
    public EmptyDateException() {
        super("OOPS!!! You have not entered the date for this task.");
    }
}
