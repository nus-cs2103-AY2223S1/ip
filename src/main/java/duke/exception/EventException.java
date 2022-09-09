package duke.exception;

/**
 * Event exception for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class EventException extends DukeException {
    /**
     * Constructs a new default EventException.
     */
    public EventException() {
        super("The description and time of event cannot be empty.");
    }

    /**
     * Constructs a new EventException based on error string.
     *
     * @param error the error message string.
     */
    public EventException(String error) {
        super(error);
    }
}
