package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates tasks that occurs at a specific date.
 *
 * @author fannyjian
 */
public class Event extends Task {

    private static final DateTimeFormatter ACCEPT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TO = DateTimeFormatter.ofPattern("dd MMM yyyy");
    protected LocalDate at;

    /**
     * Creates a new Event as described happening on a specified date.
     *
     * @param description Event title.
     * @param at Date of occurrence.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at, ACCEPT);
    }

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
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

