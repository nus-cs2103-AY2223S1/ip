package dukeexceptions;

/**
 * Thrown when a string does not follow a specified datetime format during validation.
 * A string is passed to the constructor to specify the correct datetime format to follow.
 */
public class WrongDatetimeFormatException extends DukeException {

    public WrongDatetimeFormatException(String format) {
        super(String.format("Wrong date and time format provided! Please enter it in this format: %s", format));
    }
}
