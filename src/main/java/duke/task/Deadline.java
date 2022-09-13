package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.utilities.DukeException;

/**
 * The Deadline Class.
 */
public class Deadline extends Task {
    /** Deadlines have a time to be completed by that is a LocalDateTime. */
    protected LocalDateTime by;

    /**
     * Constructor for Deadline objects.
     *
     * @param description The description of the task.
     * @param by The time by which said task must be completed.
     * @throws DukeException For Duke related exceptions.
     */
    public Deadline(String description, LocalDateTime by) throws DukeException {
        super(description);
        this.by = by;
        if (description.isBlank()) {
            throw new DukeException("The description of deadline task is missing information!");
        }
    }

    /**
     * Gets the deadline.
     *
     * @return Returns the deadline by which the event must be completed in string format.
     */
    public String getBy() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return this.by.format(pattern);
    }

    /**
     * String representation of a deadline task object.
     *
     * @return Returns the String representation of the current object.
     */
    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + this.by.format(pattern) + ")";
    }
}
