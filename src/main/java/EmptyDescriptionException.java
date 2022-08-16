public class EmptyDescriptionException extends DukeException{
    public EmptyDescriptionException() {
        super("Description cannot be empty");
    }
}
