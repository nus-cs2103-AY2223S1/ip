package exceptions;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String errorString) {
        super(errorString);
    }
}
