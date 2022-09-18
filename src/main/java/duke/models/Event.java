package duke.models;

import duke.utils.Interval;

public class Event extends Task {
    protected FormattedDate formattedDate;
    protected boolean isRecurring;
    protected Interval interval = Interval.None;

    public Event(String description, String at) {
        super(description);
        this.formattedDate = new FormattedDate(at);
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.formattedDate = new FormattedDate(at);
    }

    public Event(String description, boolean isDone, String at, Interval interval) {
        super(description, isDone, interval);
        this.formattedDate = new FormattedDate(at);
    }

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