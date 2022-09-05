package ava.exception;

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
