package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class to model an Event object.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Constructs a new instance of Event.
     *
     * @param description the event description
     * @param at the local date of the event
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a String representation for storage purpose.
     *
     * @return String representation for storage purpose
     */
    @Override
    public String toStorageString() {
        return "E" + super.toStorageString() + " | "
                + this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns a String representation for Event.
     *
     * @return String representation for Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Event) {
            Event temp = (Event) obj;
            return temp.description.equals(this.description) && temp.at.equals(this.at)
                    && temp.isDone == this.isDone;
        }
        return false;
    }
}
