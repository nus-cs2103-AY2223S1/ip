package ava.exception;

/**
 * Used when the input date does not follow the expected format.
 */
public class WrongTimeFormatException extends AvaException {
    public WrongTimeFormatException() {
        super("Input format is wrong. Please input in yyyy-MM-dd HH:mm format.");
    }
}
