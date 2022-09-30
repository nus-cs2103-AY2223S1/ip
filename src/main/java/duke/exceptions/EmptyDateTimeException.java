package duke.exceptions;

/**
 * Represents an EmptyDateTimeException class which is inherited from the Exception class and
 * occurs when the user enters an empty date or time argument
 */
public class EmptyDateTimeException extends Exception {

    /**
     * Constructs an EmptyDateTimeException with standard message
     */
    public EmptyDateTimeException() {
        super("The folly of youth to cheat Time! Specify date and time to add events and deadlines...");
    }
}
