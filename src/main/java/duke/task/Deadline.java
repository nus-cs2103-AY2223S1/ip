package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline Task.
 * Inherits from Task.
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Creates the Deadline Task.
     *
     * @param description The Deadline at hand.
     * @param by When is the Deadline is due.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates the Deadline Task.
     *
     * @param description The Deadline at hand.
     * @param by When the Deadline is due.
     * @param isDone Whether the Deadline is has been completed.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the by variable.
     *
     * @return The by variable.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Returns the String representation of Deadline.
     *
     * @return The String representation of Deadline showing the type, status, and description.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
