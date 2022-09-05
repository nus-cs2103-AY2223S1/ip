package duke;


/**
 * Event class representing user events.
 */
public class Event extends Task {

    private String time;

    /**
     * Creates a new Event object with a specified description and timing.
     * @param description Description of the event.
     * @param time Time of the event.
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Changes the description of this Event.
     * @param newDesc The new description of this Event.
     */
    public void setDescription(String newDesc) {
        this.description = newDesc;
    }

    /**
     * Changes the time of this Event
     * @param newTime The new time of this Event.
     */
    public void setTime(String newTime) {
        this.time = newTime;
    }
    /**
     * String representation of this Event, with details such as its type, description,
     * timing, and whether it is completed or not.
     * @return String representation of this Event.
     */
    @Override
    public String toString() {
        return "  [E] [" + this.getStatusIcon() + "] " + this.description + " (at: " + this.time + ")";
    }
}
