public class DukeInvalidCommandException extends DukeException {
    public DukeInvalidCommandException(String command) {
        super("Command " + command + " not found. Please try again.");
    }
}
