package dukeexceptions;

public class NoDescriptionException extends DukeException {
    public NoDescriptionException(String temp) {
        super(String.format(":-( OOPS! the description of %s cannot be empty. try again!", temp));
    }
}
