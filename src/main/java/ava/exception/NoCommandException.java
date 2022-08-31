package ava.exception;

/**
 * Class to represent Duke.Exception.NoCommandException.
 */
public class NoCommandException extends Exception {
    /**
     * The Constructor for NoCommandException.
     *
     * @param message Message of the exception.
     */
    public NoCommandException(String message) {
        super(String.format("Why is there no command?"));
    }
}
