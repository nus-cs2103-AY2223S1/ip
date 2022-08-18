package models;

/**
 * A child class Event that inherits properties description and isDone from Task
 */
public class Event extends Task {
    protected String at;

    /**
     * Initialises an Event object with description and date of the event
     * @param description A short description of the event
     * @param at The date where this event is happening
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
