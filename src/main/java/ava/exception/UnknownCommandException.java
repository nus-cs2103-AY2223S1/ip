package ava.exception;

/**
 * Class to represent Duke.Exception.NoTimeException.
 */
public class UnknownCommandException extends AvaException {
    public UnknownCommandException(String input) {
        super("Command \"" + input + "\" not found.");
    }
}
