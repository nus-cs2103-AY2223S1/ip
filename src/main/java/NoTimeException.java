/**
 * Class to represent NoTimeException.
 */
public class NoTimeException extends Exception {
    /**
     * The Constructor for NoTimeException
     * @param message
     */
    public NoTimeException(String message) {
        super(String.format("☹ OOPS!!! The Time of " + message + " is missing!"));
    }
}
