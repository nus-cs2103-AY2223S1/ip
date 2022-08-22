package duke.tasks;

public class Event extends Task {

    private String eventDateTime;

    /**
     * Standard constructor for an event
     * @param description The description of the event
     * @param eventDateTime The date and time of the event occurring
     */
    public Event(String description, String eventDateTime) {
        super(description);
        this.eventDateTime = eventDateTime;
    }

    /**
     * Overloaded constructor to allow for creation of pre-completed events
     * @param description The description of the event
     * @param isDone Marks the event as having been completed or not
     * @param eventDateTime The event's date and time of occurrence
     */
    public Event(String description, boolean isDone, String eventDateTime) {
        super(description, isDone);
        this.eventDateTime = eventDateTime;
    }

    @Override
    public String getSaveString() {
        return "EVENT,," + super.getSaveString() + this.eventDateTime;
    }


    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.eventDateTime);
    }
}
