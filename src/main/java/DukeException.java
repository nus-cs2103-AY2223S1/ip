public class DukeException extends Exception {
    public DukeException(String message) {
        super(String.format("Oops! %s", message));
    }
}
