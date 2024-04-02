package duke.exception;

/**
 * Represents an error during parsing of input from the user.
 * This error will occur when the time format provided is wrong.
 */
public class DateTimeException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + "Please use the specified date-time format: yyyy-MM-dd HH:mm,"
            + " or yyyy-MM-dd if you want the time to be 23:59";
    }
}
