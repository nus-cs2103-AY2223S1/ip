package duke.models;

import duke.utils.Interval;

/**
 * A class representing an Event task with an "at" date.
 */
public class Event extends Task {
    protected FormattedDate formattedDate;

    /**
     * Event Constructor.
     *
     * @param description Event description.
     * @param at Date at which the event occurs.
     */
    public Event(String description, String at) {
        super(description);
        this.formattedDate = new FormattedDate(at);
    }

    /**
     * Event Constructor.
     *
     * @param description Event description.
     * @param isDone Completion status.
     * @param at Date at which the event occurs.
     * @param interval Interval at which the event recurs.
     */
    public Event(String description, boolean isDone, String at, Interval interval) {
        super(description, isDone, interval);
        this.formattedDate = new FormattedDate(at);
    }

    /**
     * Event Constructor.
     *
     * @param description Event description.
     * @param isDone Completion status.
     * @param formattedDate Date at which the event occurs.
     * @param interval Interval at which the event recurs.
     */
    public Event(String description, boolean isDone, FormattedDate formattedDate, Interval interval) {
        super(description, isDone, interval);
        this.formattedDate = formattedDate;
    }

    /**
     * Gets the formatted date of the Event.
     *
     * @return Event at date.
     */
    public FormattedDate getFormattedDate() {
        return this.formattedDate;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.formattedDate);
    }
}
