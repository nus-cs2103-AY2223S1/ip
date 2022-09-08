package mew;

public class MewDateTimeParseException {
    public static class InputOverFlowException extends MewException {
        public InputOverFlowException(String msg) {
            super(msg);
        }
    }

    public static class InvalidDateTimeFormatException extends MewException {
        public InvalidDateTimeFormatException(String msg) {
            super(msg);
        }
    }
}
