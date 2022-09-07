package ava.exception;

/**
 * Represents a custom exception class that captures all possible runtime exceptions that could happen.
 */
public class AvaException extends Exception {
    /** Default AvaException constructor. */
    public AvaException() {
        super();
    }

    /**
     * AvaException constructor.
     *
     * @param message The error message.
     */
    public AvaException(String message) {
        super(message);
    }
}
