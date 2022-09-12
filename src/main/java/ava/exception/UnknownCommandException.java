package ava.exception;

/**
 * Used when users type nonsense as Command.
 */
public class UnknownCommandException extends AvaException {
    public UnknownCommandException(String input) {
        super("Command \"" + input + "\" not found.");
    }
}
