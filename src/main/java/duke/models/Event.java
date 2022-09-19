package duke.models;

import duke.utils.Interval;

/**
 * A class representing an Event task with an "at" date.
 */
public class Event extends Task {
    protected FormattedDate atDate;

    /**
     * Event Constructor.
     *
     * @param description Event description.
     * @param at Date at which the event occurs.
     */
    public Event(String description, FormattedDate at) {
        super(description);
        this.atDate = at;
    }

    /**
     * Event Constructor.
     *
     * @param description Event description.
     * @param isDone Completion status.
     * @param atDate Date at which the event occurs.
     * @param interval Interval at which the event recurs.
     */
    public Event(String description, boolean isDone, FormattedDate atDate, Interval interval) {
        super(description, isDone, interval);
        this.atDate = atDate;
    }

    /**
     * Gets the at date of the Event.
     *
     * @return Date of Event.
     */
    public FormattedDate getEventDate() {
        return this.atDate;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.atDate);
    }
}
