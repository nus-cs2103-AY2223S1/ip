package duke.task;

import duke.utilities.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Encapsulates a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a new {@code Deadline} with given description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     * @throws DukeException
     */
    public Deadline(String description, LocalDateTime by) throws DukeException {
        super(description);
        if (description.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        this.by = by;
    }

    /**
     * Returns the string representation of this {@code by} for display.
     * @return The string representation of this {@code by} for display.
     */
    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mma")) + ")";
    }
}
