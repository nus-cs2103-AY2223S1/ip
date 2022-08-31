package yilia.exception;

/**
 * Represents an exception to be thrown when the command is unknown.
 */
public class YiliaException extends Exception {
    /**
     * Returns the message of the exception.
     *
     * @return The message of the exception.
     */
    @Override
    public String getMessage() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
