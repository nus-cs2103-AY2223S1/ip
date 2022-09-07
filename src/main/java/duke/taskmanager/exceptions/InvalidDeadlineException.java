package duke.taskmanager.exceptions;

/**
 * Exception that is thrown when there is an invalid deadline.
 */
public class InvalidDeadlineException extends Exception {
    /**
     * Exception that handles invalid date formats for deadline tasks.
     */
    public InvalidDeadlineException(String dateFormat) {
        super("You have an invalid deadline!\nDeadlines should be in the format: " + dateFormat + "\n");
    }
}
