package duke.exception;

/**
 * Exception for invalid command
 */
public class InvalidCommandException extends Exception {
    /**
     * Method to return a string representation of the exception
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return "Invalid command. Please try again.";
    }
}
