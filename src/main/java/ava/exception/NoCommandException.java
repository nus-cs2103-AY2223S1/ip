package ava.exception;

/**
 * Used when the user's input is empty or only contains whitespace.
 */
public class NoCommandException extends AvaException {
    public NoCommandException() {
        super("Why is there no command?");
    }
}
