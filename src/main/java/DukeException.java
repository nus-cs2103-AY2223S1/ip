/**
 * Exception for the Duke application.
 */
public class DukeException extends Exception {
    // Some commonly used exceptions.
    public final static DukeException unknownCommand = new DukeException("Unknown command!");
    public final static DukeException noIndex = new DukeException("Please provide an index!");
    public final static DukeException invalidIndex = new DukeException("Invalid index!");
    public final static DukeException invalidDate = new DukeException("Invalid date!");

    /**
     * Constructor for a Duke Exception, that takes in a message.
     *
     * @param message Message for the Duke Exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
