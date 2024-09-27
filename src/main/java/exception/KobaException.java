package exception;

/**
 * This class encapsulates the exceptions that are specific to Koba.
 */
public class KobaException extends Exception {

    /**
     * Constructs a KobaException.
     *
     * @param message what is the error.
     */
    public KobaException(String message) {
        super(message);
    }
}
