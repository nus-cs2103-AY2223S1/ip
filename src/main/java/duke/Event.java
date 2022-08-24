package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  A class which encapsulates the event type of task.
 *  @author  Chen Guanzhou
 *  @version v1
 */
public class Event extends Task {
    protected LocalDate date;

    public Event(String description, LocalDate eventDate) {
        super(description);
        this.date = eventDate;
    }

    /**
     * String representation of a event object.
     * @return The string representing the object with the state of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d YYY")) + ")";
    }
}