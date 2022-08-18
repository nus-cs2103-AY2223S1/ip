public class NoDescriptionException extends DukeException {
    public NoDescriptionException(String temp) {
        super(String.format(":-( OOPS! The description of %s cannot be empty. Try again!", temp));
    }
}
