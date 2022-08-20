/**
 * Exception for the Duke application.
 */
public class DukeException extends Exception {
    // Some commonly used exceptions.
    public final static DukeException invalidIndex = new DukeException("Invalid index!");
    public final static DukeException invalidDate = new DukeException("Invalid date!");
    public final static DukeException badData = new DukeException("Bad tasks data!");

    /**
     * Constructor for a Duke Exception, that takes in a message.
     *
     * @param message Message for the Duke Exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
