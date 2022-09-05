package ava.exception;

/**
 * Used when the time description is empty in Event/Deadline tasks.
 */
public class NoTimeException extends AvaException {
    public NoTimeException() {
        super("The Time is missing!");
    }
}
