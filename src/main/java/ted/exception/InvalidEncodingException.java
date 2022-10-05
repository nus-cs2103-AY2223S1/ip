package ted.exception;

/**
 * This is to indicate file might be corrupted and
 * our program cannot parse it.
 */
public class InvalidEncodingException extends Exception {
    public InvalidEncodingException() {
        super("Error while decoding: invalid encoding format");
    }
}
