package duke.exception;

/**
 * Exception for input with no description
 */
public class NoDescriptionException extends Exception {
    /**
     * Method to return a string representation of the exception
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return "Your task has to have description :<";
    }
}
