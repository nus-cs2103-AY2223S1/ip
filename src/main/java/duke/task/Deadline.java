package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline type Task.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Creates a Deadline that is not done.
     *
     * @param description Description of this Deadline.
     * @param deadline The actual deadline for this Deadline.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Creates a Deadline with a specified done status.
     *
     * @param isDone The done status of the Deadline.
     * @param description Description of this Deadline.
     * @param deadline The actual deadline for this Deadline.
     */
    public Deadline(boolean isDone, String description, LocalDate deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("dd MMM yy")) + ")";
    }

    /**
     * Returns a String representation of the Deadline that is suitable for storing in a text file.
     *
     * @return A string representation of the Deadline for storing in a text file.
     */
    public String toDbString() {
        return "D" + " | " + super.toDbString() + " | "
                + deadline.format(DateTimeFormatter.ofPattern("dd MMM yy"));
    }
}
