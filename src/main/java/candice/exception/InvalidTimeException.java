package candice.exception;

/**
 * Abstraction for exceptions that are thrown when the time inputted for a task, specifically for deadlines and events,
 * does not follow the format of a LocalTime or does not exist.
 */
public class InvalidTimeException extends Exception {
    /**
     * Constructor for an exception thrown when the deadline or event command inputted has an invalid time.
     */
    public InvalidTimeException() {
        super("Invalid time bro. You need to write the time as a 24-hour time (1600). For events, you are required "
                + "to give the start and end time (1400-1600).");
    }
}
