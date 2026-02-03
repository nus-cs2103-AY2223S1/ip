package duke.exceptions;

/**
 * Represents a DateTimeFormatException which is thrown when
 * a date or time is given in an incorrect format.
 */
public class DateTimeFormatException extends Exception {

    /**
     * Constructs a DateTimeFormatException with Duke Aemon's standard message.
     */
    public DateTimeFormatException() {
        super("Time itself is precise. Use dates as yyyy-MM-dd, and hours as HH:mm.");
    }
}
