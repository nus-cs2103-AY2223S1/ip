package ava.exception;

/**
 * Class to represent Duke.Exception.WrongTimeFormatException
 */
public class WrongTimeFormatException extends Exception {
    /**
     * The Constructor for WrongTimeFormatException.
     */
    public WrongTimeFormatException() {
        super(String.format("☹ OOPS!!! Please input format is wrong. Please input in yyyy-MM-dd HH:mm format."));
    }
}
