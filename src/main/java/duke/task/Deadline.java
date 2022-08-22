package duke.task;

import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 * @author neosunhan
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Constructor for a Deadline.
     * @param description description of the task
     * @param by deadline of the task
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toStorage() {
        return "D|" + super.toStorage() + "|" + this.by.format(Deadline.INPUT_DATE_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(Deadline.OUTPUT_DATE_FORMAT) + ")";
    }
}
