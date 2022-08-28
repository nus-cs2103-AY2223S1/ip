package duke.task;

import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Constructor for a Deadline.
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toStorage() {
        return "D|" + super.toStorage() + "|" + by.format(Deadline.INPUT_DATE_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(Deadline.OUTPUT_DATE_FORMAT) + ")";
    }
}
