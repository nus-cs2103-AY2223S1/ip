package duke.tasks;

import java.time.LocalDate;

/**
 * This class represents an event. It contains the description
 * and the date of the event.
 */
public class Event extends Task {

    private final LocalDate localDate;

    /**
     * Constructs an event with the specified description and date.
     * @param name Description of event.
     * @param date The day the event happens at.
     */
    public Event(String name, LocalDate date) {
        super(name);
        localDate = date;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + localDate.toString() + ")";
    }

    @Override
    public String toDataString() {
        return String.format("[E] | %d | %s | %s",
                isMarked() ? 1 : 0,
                getName(),
                localDate.toString());
    }
}
