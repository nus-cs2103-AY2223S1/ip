package ava.exception;

/**
 * Class to represent Duke.Exception.NoDescriptionException.
 */
public class NoDescriptionException extends AvaException {
    public NoDescriptionException() {
        super("Missing task description!");
    }
}
