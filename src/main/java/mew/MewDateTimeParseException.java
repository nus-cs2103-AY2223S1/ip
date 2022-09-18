package mew;

/**
 * A custom Exception class that handles errors in parsing date and time.
 */
public class MewDateTimeParseException {
    /**
     * A custom Exception class that handles inputs that are too long (overflowing).
     */
    public static class InputOverFlowException extends MewException {
        public InputOverFlowException(String msg) {
            super(msg);
        }
    }

    /**
     * A custom Exception class that handles invalid inputs.
     */
    public static class InvalidDateTimeFormatException extends MewException {
        public InvalidDateTimeFormatException(String msg) {
            super(msg);
        }
    }
}
