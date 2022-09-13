package nyanduke;

/**
 * The NyanDukeException class represents an exception
 * that is unique to NyanDuke.
 */
public class NyanDukeException extends Exception {
    /**
     * Constructs a new NyanDukeException with an error message.
     *
     * @param message A string that describes the error which occurred.
     */
    public NyanDukeException(String message) {
        super(message);
    }
}
