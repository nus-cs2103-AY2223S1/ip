package duke.exception;

/**
 * The DukeException class represents Exceptions that may be thrown
 * when using the Duke task manager.
 *
 * @author Edric Yeo
 */
public abstract class DukeException extends Exception {
    private String message;

    /**
     * Constructor for a DukeException.
     *
     * @param message The String describing the issue causing the Exception.
     */
    public DukeException(String message) {
        super(":( OOPS!! " + message);
    }

    /**
     * Method that returns the String representation of the DukeException.
     *
     * @return String describing the issue causing the Exception.
     */
    @Override
    public String toString() {
        return ":( OOPS!! " + this.message;
    }
}
