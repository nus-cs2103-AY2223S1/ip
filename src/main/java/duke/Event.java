package duke;


public class Event extends Task {

    private final String time;

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
     * String representation of this Event, with details such as its type, description,
     * timing, and whether it is completed or not.
     * @return String representation of this Event.
     */
    @Override
    public String toString() {
        return "  [E] [" + this.getStatusIcon() + "] " + this.description + " (at: " + this.time + ")";
    }
}
