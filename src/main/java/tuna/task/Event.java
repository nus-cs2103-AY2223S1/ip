package tuna.task;

import tuna.TunaException;

/**
 * Represents an Event task.
 */
public class Event extends TimeBasedTask {
    /**
     * Creates an event with a start time.
     *
     * @param description the description of the event.
     * @param at the start time of the event.
     * @throws TunaException Exception thrown when the date and time provided is not formatted correctly.
     */
    public Event(String description, String at) throws TunaException {
        super(description, "E", at);
    }

    /**
     * Returns string representation of the Event object.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString() + " (at: " + parseDateTime(this.getDateTime()) + ")";
    }
}
