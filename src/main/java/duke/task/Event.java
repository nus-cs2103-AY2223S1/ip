package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class representing an event task.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructs a new event task.
     *
     * @param description the description of this event
     * @param isDone boolean indicating whether this task is done
     * @param at LocalDate indicating when the event will occur
     */
    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns the string format of this event task to be saved in the save file.
     *
     * @return a string representation of this event task in the format it is saved in the save file.
     */
    public String toFileFormat() {
        String isDone = this.isDone ? "1" : "0";
        return "E | " + isDone + " | " + this.description + " | " + this.at;
    }

    /**
     * String representation of this event.
     *
     * @return a string representing this event.
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * Returns a boolean indicating if this event is the same as the obj.
     *
     * @param obj the Object to be compared to
     * @return true if this event and obj are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event tmp = (Event) obj;
            return super.equals(tmp) && this.at.equals(tmp.at);
        }
        return false;
    }

}
