package yilia.exception;

/**
 * Represents an exception to be thrown when the given time format is invalid.
 */
public class TimeFormatException extends Exception {
    /**
     * Returns the message of the exception.
     *
     * @return The message of the exception.
     */
    @Override
    public String getMessage() {
        return "Please input a date in the correct format";
    }
}
