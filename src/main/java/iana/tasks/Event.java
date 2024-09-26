package iana.tasks;

import java.io.Serializable;

import iana.utils.DateTime;

/**
 * Event task.
 */
public class Event extends Task implements Serializable {

    /** Time that the event starts */
    protected String eventTime;

    /** The event description */
    protected String event;

    /**
     * Constructor for Event class.
     * 
     * @param event full event description.
     * @param eventTime time the event occurs.
     * @param isCompleted true if event has ended.
     */
    public Event(String event, String eventTime, boolean isCompleted) {
        super(event, "event", isCompleted);
        this.eventTime = DateTime.parseToString(eventTime);
    }

    @Override
    public String toFileData() {
        return "E | " + super.toFileData() + "| " + this.eventTime;
    }

    @Override
    public String toString() { 
        return String.format("[E]%s (at: %s)", super.toString(), this.eventTime);
    }
}