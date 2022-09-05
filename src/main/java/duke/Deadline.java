package duke;

import java.time.LocalDate;

/**
 * An abstraction of a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructor for the Deadline class.
     *
     * @param description The task description.
     * @param deadline The task deadline.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public LocalDate getDate() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
