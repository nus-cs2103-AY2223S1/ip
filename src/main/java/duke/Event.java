package duke;

import java.time.LocalDate;

/**
 * Represents an <code>Event</code> and its time.
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Construct an <code>Event</code>.
     *
     * @param description Description of event.
     * @param at The time of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    /**
     * Construct an <code>Event</code>.
     *
     * @param description Description of event.
     * @param isDone State of the event.
     * @param at The time of the event.
     */
    public Event(String description, boolean isDone , String at) {

        super(description, isDone);
        this.at = LocalDate.parse(at);
    }

    /**
     * Returns string representaion of the <code>Event</code>.
     *
     * @return string representaion of the <code>Event</code>.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " ( at: " + at + " )";
    }

    /**
     * Returns a string formatted to be read by a <code>Storage</code> object.
     *
     * @return formatted string.
     */
    @Override
    public String toStorageFormat() {
        return "E | " + super.toStorageFormat() + " | " + at;
    }

}
