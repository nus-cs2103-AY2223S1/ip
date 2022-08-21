package dukeExceptions;

public class WrongDatetimeFormatException extends Exception {

    /**
     * Thrown when a string does not follow a specified datetime format during validation.
     * A string is passed to specify the correct datetime format to follow.
     * @param format The correct datetime format to follow during parsing.
     */
    public WrongDatetimeFormatException(String format) {
        super(String.format("Wrong date and time format provided! Please enter it in this format: %s", format));
    }
}
