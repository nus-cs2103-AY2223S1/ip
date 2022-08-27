package ava.exception;

/**
 * Class to represent Duke.Exception.NoDescriptionException.
 */
public class NoDescriptionException extends Exception {
    /**
     * The Constructor for NoCommandException.
     *
     * @param message Message of the exception.
     */
    public NoDescriptionException(String message) {
        super(String.format("☹ OOPS!!! The description of a " + message + " cannot be empty."));
    }
}
