package duke;

import java.time.LocalDate;

/**
 * An abstraction for a task with a date.
 */
public class Event extends Task {
    private LocalDate eventDate;

    /**
     * Constructor for the Event class.
     *
     * @param description The task description.
     * @param eventDate The event date.
     */
    public Event(String description, LocalDate eventDate) {
        super(description);
        this.eventDate = eventDate;
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
        return 0;
    }

    @Override
    public void updateDate() {
        return;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDate + ")";
    }
}
