package duke.exceptions;

/**
 * Represents an DateTimeFormatException class which is inherited from the Exception class and
 * occurs when the user enters date and time in an incorrect format
 */
public class DateTimeFormatException extends Exception {

    /**
     * Constructs an DateTimeFormatException with standard message
     */
    public DateTimeFormatException() {
        super("Write all dates in the format of yyyy-MM-dd and times in the format of HH:mm");
    }
}
