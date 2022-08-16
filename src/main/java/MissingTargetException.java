public class MissingTargetException extends DukeException {

    public MissingTargetException(String command) {
        super("OOPS!!! Please specify which task to " + command);
    }
}
