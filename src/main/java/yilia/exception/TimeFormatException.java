package yilia.exception;

/**
 * Represents an exception to be thrown when the given time format is invalid.
 */
public class TimeFormatException extends Exception {
    @Override
    public String getMessage() {
        return "Please input a date in the correct format";
    }
}
