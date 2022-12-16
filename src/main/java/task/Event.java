package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An Event is a type of task that has a date that the event is
 * occurring on and a description of the event.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Create new Event
     * @param description description of event
     * @param date date of event
     */
    public Event(String description, LocalDate date) {
        super(description);
        at = date;
    }

    /**
     * Create new Event
     * @param description description of event
     * @param date date of event
     * @param isDone boolean that is true when event is marked done otherwise it is false
     */
    public Event(String description, LocalDate date, boolean isDone) {
        super(description, isDone);
        at = date;
    }

    /**
     * Set date of event to given date
     * @param date Date that the event is to be set to
     */
    public void setAt(LocalDate date) {
        at = date;
    }

    @Override
    public String getSaveFormat() {
        return "E"
                + " | "
                + (getIsDone() ? 1 : 0)
                + " | "
                + getDescription()
                + " | "
                + at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
    }
}
