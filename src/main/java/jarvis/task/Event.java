package jarvis.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  Represents an Event which is a subclass of Task.
 *
 */
public class Event extends Task {

    private LocalDate at;

    /**
     * Returns a new Event Object with the given description and date.
     *
     * @param description Description of the Event.
     * @param date Date of the Event.
     */
    public Event(String description, String date) {
        super(description);
        this.at = LocalDate.parse(date);
    }

    /**
     * Returns String description of Deadline in the following format:
     * [E] *Event Description* (at: *date*)
     * @return String description of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public LocalDate getAt() {
        return at;
    }
}
