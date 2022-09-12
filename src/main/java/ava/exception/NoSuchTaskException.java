package ava.exception;

/**
 * Used when the task chosen is not available.
 */
public class NoSuchTaskException extends AvaException {
    public NoSuchTaskException() {
        super("Task is not available! Please enter the right number!");
    }
}
