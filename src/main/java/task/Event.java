package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates tasks that occurs at a specific date.
 *
 * @author fannyjian
 */
public class Event extends Task {

    private static final DateTimeFormatter TO = DateTimeFormatter.ofPattern("dd MMM yyyy");
    protected LocalDate at;

    /**
     * Creates a new Event as described happening on a specified date.
     *
     * @param description Event title.
     * @param at Date of occurrence.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public boolean checkDescription(Event eventOne, Event eventTwo) {
        return eventOne.description.equals(eventTwo.description);
    }

    public boolean checkAt(Event eventOne, Event eventTwo) {
        return eventOne.at.equals(eventTwo.at);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event obj = (Event) o;

            if (checkDescription(obj, this) || checkAt(obj, this)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Returns the string representation of the Event with name and date specified.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " AT " + this.at.format(TO);
    }
}

