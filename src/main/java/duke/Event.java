package duke;

/**
 * Event class that extends from Task
 * @author amresh A0235398R
 */
public class Event extends Task {
    protected String duration;

    /**
     * Constructor for Event object
     *
     * @param description Description of Event
     * @param duration Duration to be completed in
     */
    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if (obj instanceof Event) {
            Event event = (Event) obj;
            return super.equals(event) && this.duration.equals(event.duration);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration + ")";
    }
}
