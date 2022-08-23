package exceptions;

public class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException(String errorString) {
        super(errorString);
    }
}
