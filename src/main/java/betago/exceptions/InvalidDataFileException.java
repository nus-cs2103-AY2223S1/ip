package betago.exceptions;

/**
 * InvalidDataFileException that is thrown when data file consist of invalid entries.
 */
public class InvalidDataFileException extends Exception {
    public InvalidDataFileException(String message) {
        super(message);
    }
}
