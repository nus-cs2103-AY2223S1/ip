package bestie.exceptions;

/**
 * Custom exception class for handling exceptions specific to Bestie.
 */
public class BestieException extends Exception {
    /**
     * Constructs BestieException.
     */
    public BestieException(String errorMessage) {
        super(errorMessage);
    }
}
