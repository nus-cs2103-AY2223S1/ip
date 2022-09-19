package justin;

/**
 * Represents the exception thrown in the Duke program.
 * @author Justin Cheng.
 */
public class DukeException extends Exception {
    protected String message;

    /**
     * Constructor of the DukeException class.
     * @param message The message given in the exception.
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns the message thrown by the exception.
     * @return the message of the exception.
     */
    @Override
    public String toString() {
        return this.message;
    }
}
