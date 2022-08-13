public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super("Apologies, but " + errorMessage);
    }
}