package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {

    /** Deadline for this task */
    private String deadline;

    /**
     * Constructs a new deadline task with
     * given description and deadline.
     *
     * @param description of the task.
     * @param deadline of the task to be completed by.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + LocalDate.parse(deadline).format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + ")";
    }

    @Override
    public String toMemoryString() {
        return "D"
                + " | "
                + (this.isDone() ? "1" : "0")
                + " | "
                + this.getName()
                + " | "
                + this.deadline;
    }
}
