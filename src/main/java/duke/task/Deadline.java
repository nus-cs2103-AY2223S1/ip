package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a type of Task that has a 'done by date' to it.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the Deadline.
     * @param by The due date.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for task in save file.
     *
     * @param description Description of the Deadline.
     * @param done If the Deadline task is done or not done.
     */
    public Deadline(String description, LocalDate by, boolean done) {
        super(description, done);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Getter to access by field.
     *
     * @return The due date
     */
    public LocalDate getBy() {
        return this.by;
    }
}
