package dukeProgram;

/**
 * Event class for creating an event
 */
public class Event extends DatedJob {

    /**
     * Creates a new Event
     * @param name name of the event
     * @param dueString a date that describes the occurrence date of the event
     */
    public Event(String name, String dueString) {
        super(name, dueString, "at");
    }

    /**
     * Returns a formatted string beginning with the event tag
     * followed by the task data
     * @return a string that begins with "[E]" followed by the task data
     */
    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
