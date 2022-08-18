package exceptions;

/**
 * Custom exception class for handling known exceptions in chatbot and returning a user-friendly message.
 * Justification: other unknown exceptions may occur which must be separated
 */
public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }
}
