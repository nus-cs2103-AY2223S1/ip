public class TedException extends Exception {
    public TedException(String message) {
        super("Error: " + message);
    }
}
