package duke.exception;

public class InvalidIntegerException extends DukeException{
    private static final String errorString = " is not an integer.";

    public InvalidIntegerException(String input) {
        super(input + errorString);
    }
}
