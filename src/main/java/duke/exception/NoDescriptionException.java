package duke.exception;

/**
 * Encapsulates an Exception for input with no description
 */
public class NoDescriptionException extends Exception {
    /**
     * Returns a string representation of the exception
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return "Your task has to have description :<";
    }
}
