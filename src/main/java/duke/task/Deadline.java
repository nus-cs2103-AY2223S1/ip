package duke.task;

import java.time.LocalDate;

/**
 * Represents a task-type with description, deadline, and completion status.
 *
 * @author Tan Jun Wei
 */
public class Deadline extends Task {
    /** The deadline of the task. */
    protected LocalDate localDate;

    /**
     * Constructs a new deadline task (with default completion status).
     *
     * @param description The description of the task.
     * @param localDate The deadline of the task.
     */
    public Deadline(String description, LocalDate localDate) {
        super(description);
        this.localDate = localDate;
    }

    /**
     * Constructs a new deadline task (with specified completion status).
     *
     * @param description The description of the task.
     * @param localDate The deadline of the task.
     * @param isDone Whether the task is completed.
     */
    public Deadline(String description, LocalDate localDate, boolean isDone) {
        super(description, isDone);
        this.localDate = localDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        return "D" + ENCODING_SEPARATOR + localDate + ENCODING_SEPARATOR + super.encode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + localDate + ")";
    }
}