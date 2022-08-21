public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException(String message) {
        super("the description of a " + message + " cannot be empty.");
    }
}
