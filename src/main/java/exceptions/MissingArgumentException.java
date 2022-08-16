package exceptions;

public class MissingArgumentException extends DukeException {
    public MissingArgumentException(String errorString) {
        super(errorString);
    }
}
