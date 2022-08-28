package exception;

/**
 * This class encapsulates the exceptions that are specific to Duke.
 */
public class DukeException extends Exception{

    /**
     * Creates a DukeException.
     * @param message what is the error.
     */
    public DukeException(String message) {
        super(message);
    }
}
