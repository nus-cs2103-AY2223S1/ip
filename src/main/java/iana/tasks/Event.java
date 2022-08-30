package iana.tasks;

import iana.utils.DateTime;

/**
 * Event task.
 */
public class Event extends Task {
    protected String eventTime;
    protected String event;

    /**
     * Constructor for Event class.
     * @param event full event description.
     * @param eventTime time the event occurs.
     * @param isCompleted true if event has ended.
     */
    public Event(String event, String eventTime, boolean isCompleted) {
        super(event, "event", isCompleted);
        this.eventTime = DateTime.parseToString(eventTime);
    }

    /**
     * String representation of event to be saved in storage.
     */
    @Override
    public String toFileData() {
        return "E | " + super.toFileData() + "| " + this.eventTime;
    }

    /**
     * String representation of event.
     */
    @Override
    public String toString() { 
        return String.format("[E]%s (at: %s)", super.toString(), this.eventTime);
    }
}