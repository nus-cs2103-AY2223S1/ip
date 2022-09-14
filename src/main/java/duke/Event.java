package duke;

import java.time.LocalDate;

/**
 * An abstraction for a task with a date.
 */
public class Event extends Task {
    private LocalDate eventDate;
    private int period;

    /**
     * Constructor for the Event class.
     *
     * @param description The task description.
     * @param eventDate The event date.
     */
    public Event(String description, LocalDate eventDate, int period) {
        super(description);
        this.eventDate = eventDate;
        this.period = period;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public LocalDate getDate() {
        return eventDate;
    }

    @Override
    public int getPeriod() {
        return period;
    }

    @Override
    public void updateDate() {
        if (period == 0) {
            return;
        }
        if (!this.getMarked()) {
            return;
        }
        this.setMarked(false);
        this.eventDate = eventDate.plusDays(period);
    }

    @Override
    public String toString() {
        String ret = "[E]" + super.toString() + " (at: " + eventDate + ")";
        if (period > 1) {
            ret += " (recurs: Every " + period + " days)";
        } else if (period == 1) {
            ret += " (recurs: Every day)";
        }
        return ret;
    }
}
