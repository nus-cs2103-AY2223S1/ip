package exceptions;

public class InvalidTaskSpecificationException extends DukeException {
    public InvalidTaskSpecificationException(String errorString) {
        super(errorString);
    }
}
