package ava.exception;

/**
 * Class to represent Duke.Exception.NoTimeException.
 */
public class UnknownCommandException extends Exception {
    /**
     * The Constructor for UnknownCommandException.
     *
     * @param input Unknown input.
     */
    public UnknownCommandException(String input) {
        super("Command \"" + input + "\" not found.");
    }
}