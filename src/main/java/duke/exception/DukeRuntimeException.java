package duke.exception;

/**
 * Represents any unchecked exception the Duke application can encounter (that should have been avoided).
 */
public class DukeRuntimeException extends RuntimeException {

    /**
     * Initialise the Exception with a message.
     * @param message Message describing the exception.
     */
    public DukeRuntimeException(String message) {
        super("(Runtime Exception)" + message);
    }

}