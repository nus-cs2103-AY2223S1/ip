package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a Task that has a deadline (when the task should be finished).
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for Deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = processDate(by);
    }

    /**
     * Returns the deadline of the task.
     *
     * @return The LocalDate format of the task's deadline.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Returns a string representation of a deadline.
     *
     * @return Details regarding this deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
