package ava.exception;

/**
 * Class to represent Duke.Exception.NoTimeException.
 */
public class NoTimeException extends Exception {
    /**
     * The Constructor for NoTimeException.
     *
     * @param message Message of the exception.
     */
    public NoTimeException(String message) {
        super(String.format("☹ OOPS!!! The Time of " + message + " is missing!"));
    }
}
