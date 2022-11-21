package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class that handles the event tasks.
 */
public class Event extends DatedTask {

    /**
     * Constructs the event.
     *
     * @param description the description of the event.
     * @param at the date at which the event is held at.
     * @throws DateTimeException thrown when wrong date format is provided.
     */
    public Event(String description, LocalDate at) throws DateTimeException {
        super(description, at);
    }

    /**
     * Returns the string of event.
     *
     * @return the string of event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
