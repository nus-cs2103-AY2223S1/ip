package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class to model a Deadline object.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a new instance of Deadline.
     *
     * @param description the deadline description
     * @param by the local date of the deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a String representation for storage purpose.
     *
     * @return String representation for storage purpose
     */
    @Override
    public String toStorageString() {
        return "D" + super.toStorageString() + " | "
                + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns a String representation for Deadline.
     *
     * @return String representation for Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
