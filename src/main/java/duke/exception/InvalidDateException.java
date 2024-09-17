package duke.exception;

/**
 * Encapsulates an Exception for input with invalid date/time
 */
public class InvalidDateException extends Exception {
    /**
     * Returns a string representation of the exception
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return "Please precede your date/time with a '/by' for a deadline and an '/at' for an event";
    }
}
