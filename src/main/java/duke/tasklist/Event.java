package duke.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to represent an event.
 */
public class Event extends Task {

    /** Date and time information of the event */
    protected LocalDateTime at;

    /**
     * Constructor for an event object.
     * @param description Description of event.
     * @param at Date and time information of event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }


    /**
     * Return string representation of event object, prefixed with [E], and
     * ending with the time information of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + ")";
    }

    /**
     * Return string representation of event object for saving to file.
     */
    @Override
    public String getSavedFileFormat() {
        return "E | " + (this.isDone ? 1 : 0) + " | " + this.description
                + " | "
                + this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}
