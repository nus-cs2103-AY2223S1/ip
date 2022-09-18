package mew;

/**
 * A public Exception class, where every custom exception class will inherit from this class.
 */
public class MewException extends Exception {
    public MewException(String message) {
        super(message);
    }
}
