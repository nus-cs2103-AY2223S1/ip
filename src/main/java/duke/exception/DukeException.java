package duke.exception;

/**
 * The DukeException that represents exceptions
 * thrown by Duke.
 *
 * @author Leong Jia Hao Daniel
 */
public class DukeException extends Exception {

    /**
     * Constructs the Duke exception.
     *
     * @param message Information about the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the error message.
     *
     * @return The error message.
     */
    @Override
    public String toString() {
        return this.getMessage();
    }


}
