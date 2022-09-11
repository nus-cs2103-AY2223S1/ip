package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a new deadline
     *
     * @param description the description of the deadline
     * @param by the date of the deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of this deadline
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns a simplified string representation of this deadline
     *
     * @return simplified string representation
     */
    @Override
    public String toSimpleString() {
        return "D | " + super.toSimpleString() + " | " + this.by;
    }
}
