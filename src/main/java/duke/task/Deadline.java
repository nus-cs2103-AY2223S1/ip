package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline which is ane type of task.
 */
public class Deadline extends Task {
    private LocalDate deadlineDate;

    /**
     * Constructs a new Deadline instance with a description and a date.
     *
     * @param description Description of the deadline.
     * @param deadlineDate Date of the deadline.
     */
    public Deadline(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
