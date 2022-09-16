package duke.listobjects;

/**
 * Represents an Event which is a Listobject with a task along with the event date, start and end time
 */
public class Event extends ListObject {

    /**
     * Constructs an Event object with give task description, status and time
     * @param task String representing task description
     * @param status int with value 1 if event is completed and 0 otherwise
     * @param eventTime String representing date, start and end times of event
     */
    public Event(String task, int status, String eventTime) {
        super(task, eventTime, status);
    }

    /**
     * Returns String representation of Event object
     * @return String representing Event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + super.formatDateTime("e") + ")";
    }
}
