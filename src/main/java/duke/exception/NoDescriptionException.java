package duke.exception;

/**
 * Class to represent Duke.Exception.NoDescriptionException.
 */
public class NoDescriptionException extends Exception {
    /**
     * The Constructor for Duke.Exception.NoCommandException
     * @param message
     */
    public NoDescriptionException(String message) {
        super(String.format("☹ OOPS!!! The description of a " + message + " cannot be empty."));
    }
}
