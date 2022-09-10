package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Stores type of task with deadline.
 */
public class Deadline extends Task {

    protected LocalDate deadlineDate;

    /**
     * Constructs a deadline instance.
     * @param description Description of task.
     * @param by Deadline of task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.deadlineDate = by;
    }

    /**
     * Obtains string representation of deadline task.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public LocalDate getDeadline() {
        return this.deadlineDate;
    }
}