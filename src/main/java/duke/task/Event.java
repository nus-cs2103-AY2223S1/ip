package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a Task that has a date.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class Event extends Task {
    private LocalDate at;

    /**
     * A constructor for Deadline.
     *
     * @param description The description of the Event.
     * @param isDone Has the Event been completed.
     * @param at The date of when the Event is.
     */
    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Updates the date of the Event.
     *
     * @param newDate The new date for the Event.
     */
    @Override
    public void updateDate(LocalDate newDate) {
        at = newDate;
    }

    /**
     * Converts the Event into the String format required to be saved in the Storage.
     *
     * @return String formatted data of the Event.
     */
    @Override
    public String saveStringFormat() {
        return String.format("E | %s | %s", super.saveStringFormat(), at);
    }

    /**
     * Converts the Event into its String representation.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return " [E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
