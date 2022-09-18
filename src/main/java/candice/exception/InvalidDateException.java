package candice.exception;

/**
 * Abstraction for exceptions that are thrown when the date inputted for a task, specifically for deadlines and events,
 * does not follow the format or does not exist.
 */
public class InvalidDateException extends Exception {
    /**
     * Constructor for an exception thrown when the deadline or event command inputted has an invalid date.
     */
    public InvalidDateException() {
        super("Invalid date bro. You need to write the date as DD/MM/YYYY.");
    }
}
