package ava.exception;

/**
 * Class to represent Duke.Exception.WrongTimeFormatException
 */
public class WrongTimeFormatException extends AvaException {
    public WrongTimeFormatException() {
        super("Input format is wrong. Please input in yyyy-MM-dd HH:mm format.");
    }
}
