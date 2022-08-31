package ava.exception;

/**
 * Class to represent Duke.Exception.NoDescriptionException.
 */
public class NoDescriptionException extends Exception {
    /**
     * The Constructor for NoDescriptionException.
     *
     * @param message Message of the exception.
     */
    public NoDescriptionException(String message) {
        super(String.format("The description of a " + message + " cannot be empty."));
    }
}
