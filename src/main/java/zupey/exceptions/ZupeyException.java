package zupey.exceptions;

/**
 * Custom Exception for user-related errors
 */
public class ZupeyException extends Exception {
    public ZupeyException(String message) {
        super(message);
    }
}
