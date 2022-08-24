package duke;

import java.time.LocalDate;
import java.time.DateTimeException;

/**
 * The Event class represents an event task.
 */

public class Event extends Task {

    protected String when;
    protected LocalDate whenDate;

    /**
     * Constructor for Event.
     * @param description Description of the task.
     * @param when When the event will occur.
     */
    public Event(String description, String when) {
        super(description);
        this.when = when;
        try {
            whenDate = LocalDate.parse(this.when);
        } catch (DateTimeException e) {

        }
    }

    /**
     * Returns the string representation of the current object.
     * @return String representation of the object.
     */
    @Override
    public String toString() {
        if (whenDate != null) {
            return "[E]" + super.toString() + " (at: " + whenDate.toString() + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + when + ")";
        }
    }
}
