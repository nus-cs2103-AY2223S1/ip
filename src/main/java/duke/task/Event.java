package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Task child class that specifies an event.
 */
public class Event extends Task {

    /**
     * Initialises Event object with specified description and date.
     *
     * @param description Description for Event object
     * @param at          Date the event is on
     */
    public Event(String description, String at) {
        super(description, at, TaskType.EVENT);
    }

    @Override
    public String getSaveString() {
        return "E | " + (this.isDone() ? "1" : "0") + " | " + this.getDescription() + " | " + this.date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
