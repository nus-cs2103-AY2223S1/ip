package main.java;

/**
 * Event encapsulates an event with a date/time period.
 *
 * @author Totsuka Tomofumi
 * @version Level-4, Level-5
 */
public class Event extends Task {
    /**
     * Event date/time period.
     */
    private String time;

    /**
     * Constructor for this event.
     * @param description Description of event
     * @param time Time period of event
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns a string representation of the event.
     * @return event status and its description
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", this.time);
    }
}
