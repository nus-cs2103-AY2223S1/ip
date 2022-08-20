/**
 * This class represents tasks that start at a specific time and ends at a specific time.
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for creating an event.
     * @param description Task description from user input.
     * @param at Event time from user input.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Checks event time set by the user.
     * @return Event time in string representation.
     */
    public String getEventTime() {
        return this.at;
    }

    /**
     * Displays the task type of event as E.
     * @return E.
     */
    @Override
    public String taskType() {
        return "E";
    }

    /**
     * Displays the event with its type, status (done or undone), description and time.
     * @return Task type, status, description and time.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.at);
    }

}
