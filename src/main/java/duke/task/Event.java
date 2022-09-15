package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a Task that represents an event.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class Event extends Task {
    private static final LocalDate TODAY = LocalDate.now();
    protected LocalDate at;

    /**
     * Constructor for Event.
     *
     * @param description String representation of description.
     * @param at Date when the event is held.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    /**
     * Returns the date the event is held.
     *
     * @return LocalDate on when the event is held.
     */
    public LocalDate getAt() {
        return this.at;
    }

    /**
     * Checks if the task needs a reminder.
     *
     * @return Boolean representing whether the task needs a reminder.
     */
    @Override
    public boolean isNeedReminder() {
        LocalDate lastReminderDate = TODAY.plusDays(7);
        return getAt().compareTo(TODAY) >= 0 && getAt().compareTo(lastReminderDate) <= 0;
    }

    /**
     * Returns String representation of the event.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
