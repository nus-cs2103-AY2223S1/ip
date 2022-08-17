/**
 * Class to represent NoDescriptionException.
 */
public class NoDescriptionException extends Exception {
    /**
     * The Constructor for NoCommandException
     * @param message
     */
    public NoDescriptionException(String message) {
        super(String.format("☹ OOPS!!! The description of a " + message + " cannot be empty."));
    }
}