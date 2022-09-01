package dan;

/**
 * Dan-specific exception class
 */
public class DanException extends Exception {
    public DanException(String message) {
        super("Oh no! We ran into a problem :(\n" + message);
    }
}
