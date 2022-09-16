package mew;

/**
 * A custom Exception class that handles errors in parsing input of the user.
 */
public class MewInputParseException {
    /**
     * A custom Exception class that handles invalid separators.
     */
    public static class InvalidSeparatorException extends MewException {
        public InvalidSeparatorException(String msg) {
            super(msg);
        }
    }
}
