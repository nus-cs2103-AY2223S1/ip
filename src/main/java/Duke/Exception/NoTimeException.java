package Duke.Exception;

/**
 * Class to represent Duke.Exception.NoTimeException.
 */
public class NoTimeException extends Exception {
    /**
     * The Constructor for Duke.Exception.NoTimeException
     * @param message
     */
    public NoTimeException(String message) {
        super(String.format("☹ OOPS!!! The Time of " + message + " is missing!"));
    }
}
