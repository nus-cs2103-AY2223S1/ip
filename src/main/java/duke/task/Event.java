package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a user set task happening at a certain date.
 *
 * @author totsukatomofumi
 */
public class Event extends Task {
    /** Date the event is at. */
    private LocalDate date;

    /**
     * Constructs an event.
     *
     * @param description the description of the event.
     * @param date the date the event is at.
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return a string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (at: %s)", date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Returns a string representation of the event that conforms to how storage
     * parses from the task history file.
     *
     * @return the parsable string representation of the event.
     */
    @Override
    public String toStorageFormat() {
        return "E" + super.toStorageFormat() + this.date.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Event && super.equals(obj) && ((Event) obj).date.equals(this.date);
    }
}
