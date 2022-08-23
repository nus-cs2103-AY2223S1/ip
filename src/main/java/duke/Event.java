package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates an Event task.
 *
 * @author Andrew Lo Zhi Sheng
 * @version CS2103T AY22/23 Semester 1
 */
public class Event extends Task {
    protected LocalDate at;
    /**
     * Constructor for a event instance.
     *
     * @param description the description of the event
     * @param at the time of the event
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException("Invalid Date Format, please input it as YYYY-MM-DD");
        }
    }

    /**
     * String representation of the event.
     *
     * @return String representing this event
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * Gets the deadline of the duke.Task, if any.
     *
     * @return A LocalDate representing the duke.Task's deadline if it exists
     *         null if no such deadline exists
     */
    @Override
    public LocalDate getDeadline() {
        return this.at;
    }

    /**
     * Gets the string representation of this task for storage in a file.
     *
     * @return a String containing the task name, description, whether it is
     *         completed, and the due date.
     */
    public String encode() {
        return String.format("%s # %s # %s",
                "E",
                super.encode(),
                this.at);
    }
}
