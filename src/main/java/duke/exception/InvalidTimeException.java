package duke.exception;

/**
 * Exception for invalid time for deadline
 */
public class InvalidTimeException extends Exception {
    /**
     * Method to return a string representation of the exception
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return "Invalid time given. Please try again.";
    }
}
