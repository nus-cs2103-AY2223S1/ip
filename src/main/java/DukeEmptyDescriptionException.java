public class DukeEmptyDescriptionException extends DukeException {
    public DukeEmptyDescriptionException(String command) {
        super("Please add a description for the command " + command + ".");
    }
}
