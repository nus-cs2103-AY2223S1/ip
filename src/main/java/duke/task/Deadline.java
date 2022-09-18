package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a user set task due by a date.
 *
 * @author totsukatomofumi
 */
public class Deadline extends Task {
    /** date the deadline is due. */
    private LocalDate date;

    /**
     * Constructs a deadline.
     *
     * @param description the description of the deadline.
     * @param date the date the deadline is due.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns a string representation of the deadline.
     *
     * @return a string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Returns a string representation of the deadline that conforms to how storage
     * parses from the task history file.
     *
     * @return the parsable string representation of the deadline.
     */
    @Override
    public String toStorageFormat() {
        return "D" + super.toStorageFormat() + this.date.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Deadline && super.equals(obj) && ((Deadline) obj).date.equals(this.date);
    }
}

