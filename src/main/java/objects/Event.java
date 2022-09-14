package objects;

/**
 * Event is a Task object that has a datetime that is
 * a time period.
 */
public class Event extends Task {
    private String periodDateTime;

    public Event(String name, String periodDateTime) {
        super(name.trim());
        this.periodDateTime = periodDateTime;
    }

    /**
     * Getter function for the periodDateTime field.
     * @return string containing the datetime period
     */
    public String getDateTime() {
        return this.periodDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + getStatus() + " " + getName() + " (at: " + getDateTime() + ")";
    }
}
