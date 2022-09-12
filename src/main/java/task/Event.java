package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  A class which encapsulates the event type of task.
 *  @author  Chen Guanzhou
 *  @version v2
 */
public class Event extends Task {
    protected LocalDate date;

    public Event(String description, LocalDate eventDate) {
        super(description);
        this.date = eventDate;
    }

    /**
     * Postpones the event to be a day later.
     * @return Informs the user that the event is snoozed for a day.
     */
    @Override
    public String snooze() {
        this.date = date.plusDays(1);
        return this + "\n" + "This event has been snoozed for a day!";
    }

    /**
     * String representation of an event object.
     * @return The string representing the object with the state of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d YYY")) + ")";
    }
}