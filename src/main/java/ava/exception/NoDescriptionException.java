package ava.exception;

/**
 * Used when the task description is empty.
 */
public class NoDescriptionException extends AvaException {
    public NoDescriptionException() {
        super("Missing task description!");
    }
}
