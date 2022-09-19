package jarvis.exception;

/**
 * Exception thrown by Jarvis when error is encountered.
 */
public class JarvisException extends Exception {
    public JarvisException(String errorMessage) {
        super(errorMessage);
    }
    @Override
    public String toString() {
        return "OOPS!!! " + super.getMessage();
    }
}
