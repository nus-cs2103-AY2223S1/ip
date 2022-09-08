package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Initialises a <code>Deadline</code> task with its description and deadline.
     * @param name The description of the <code>Task</code>.
     * @param by The deadline of the <code>Task</code>.
     */
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    /**
     * Returns the date of the deadline of the <code>Deadline</code>.
     * @return The date of the deadline.
     */
    public String getBy() {
        return this.by.toString();
    }

    /**
     * Changes the date format of the deadline.
     */
    public void changeDateFormat() {
        this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the description of the <code>Deadline</code> with its deadline.
     * @return The description of the <code>Deadline</code> with its deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "(by: " + by + ")";
    }
}
