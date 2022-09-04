package ava.exception;

/**
 * Class to represent Duke.Exception.NoTimeException.
 */
public class NoTimeException extends AvaException {
    public NoTimeException() {
        super("The Time is missing!");
    }
}
