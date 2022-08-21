public class MissingInputException extends DukeException{
    public MissingInputException(String message) {
        super("Please specify which tasks to " + message);
    }
}
