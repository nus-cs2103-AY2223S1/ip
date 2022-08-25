package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a Task that has a time of occurrence.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Constructor for Event.
     *
     * @param description The description of the task.
     * @param at The task's event time.
     */
    public Event(String description, String at) {
        super(description);
        this.at = processDate(at);
    }

    /**
     * Returns the time of the event.
     *
     * @return The LocalDate format of the task's event time.
     */
    public LocalDate getAt() {
        return this.at;
    }

    /**
     * Returns a string representation of an event.
     *
     * @return Details regarding this event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
