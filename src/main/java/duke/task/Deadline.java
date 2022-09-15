package duke.task;

import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    public static final String STORAGE_CHAR = "D";
    private final LocalDate by;

    /**
     * Creates a task with a deadline.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toStorage() {
        return STORAGE_CHAR + STORAGE_SEPARATOR + super.toStorage() + STORAGE_SEPARATOR
                + by.format(Deadline.INPUT_DATE_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(Deadline.OUTPUT_DATE_FORMAT) + ")";
    }
}
